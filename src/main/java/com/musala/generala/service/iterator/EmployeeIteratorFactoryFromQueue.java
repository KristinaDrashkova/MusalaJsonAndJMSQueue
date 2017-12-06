package com.musala.generala.service.iterator;

import com.musala.generala.models.Employee;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jms.*;
import javax.jms.Queue;
import java.io.*;
import java.util.*;

public class EmployeeIteratorFactoryFromQueue implements IEmployeeIteratorFactory {
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeIteratorFactoryFromQueue.class);
    private static final String QUEUE_NAME = "test_queue";
    private static final String BROKER_URL = "tcp://localhost:61616";
    private String data;
    private String applicationPropertiesFilePath;


    public EmployeeIteratorFactoryFromQueue(String appPropFilePath) throws JMSException {
        this.applicationPropertiesFilePath = appPropFilePath;
        this.data = consumeMessagesFromQueue();
    }

    public Iterator<Employee> createEmployeeIterator() throws IOException {
        Map<String, String> applicationPropertiesData;
        try {
            applicationPropertiesData = getDataFromApplicationPropertiesFile();
        } catch (IOException e) {
            LOGGER.error("There was problem loading the file: {}", applicationPropertiesFilePath);
            throw e;
        }
        return new EmployeeIterator(new StringReader(data), applicationPropertiesData);
    }

    private Map<String, String> getDataFromApplicationPropertiesFile() throws IOException {
        Map<String, String> applicationPropertiesData = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(applicationPropertiesFilePath));
            Properties properties = new Properties();
            properties.load(reader);
            reader.close();
            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                applicationPropertiesData.put(key, value);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("There is no such file: {}", applicationPropertiesFilePath);
            throw e;
        } catch (IOException e) {
            LOGGER.error("There was problem loading the file: {}", applicationPropertiesFilePath);
            throw e;
        }

        return applicationPropertiesData;
    }

    private static String consumeMessagesFromQueue() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection;
        StringBuilder data = new StringBuilder();
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(queue);

            while (true) {
                Message message = consumer.receive(2500);
                if (message instanceof TextMessage) {
                    data.append(((TextMessage)message).getText());
                }
                else{
                    session.close();
                    connection.close();
                    break;
                }
            }
        } catch (JMSException e) {
            LOGGER.error("There was problem with the connection to MQ");
            throw e;
        }
        return data.toString();
    }
}
