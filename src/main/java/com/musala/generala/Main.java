package com.musala.generala;

import com.musala.generala.exeptions.NoEmployeesException;
import com.musala.generala.service.iterator.EmployeeIteratorFactoryFromQueue;
import com.musala.generala.service.EmployeeService;
import com.musala.generala.service.ImplEmployeeService;
import com.musala.generala.service.iterator.EmployeeIteratorFactory;

import javax.jms.JMSException;
import java.io.IOException;


public class Main {
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "src/main/resources/application.properties";
    private static final String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) throws NoEmployeesException, IOException, JMSException {
        EmployeeIteratorFactory employeeIteratorFactory =
                new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_FILE_PATH, BROKER_URL);
        EmployeeService employeeService = new ImplEmployeeService(employeeIteratorFactory);
        employeeService.logEmployeeInfo();
    }
}
