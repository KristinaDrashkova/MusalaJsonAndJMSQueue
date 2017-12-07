package com.musala.generala;

import com.musala.generala.models.Employee;
import com.musala.generala.service.iterator.EmployeeIteratorFactoryFromQueue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.jms.JMSException;
import javax.json.stream.JsonParser;
import java.io.IOException;
import java.util.Iterator;


public class EmployeeIteratorTest {
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "src/main/resources/application.properties";
    private static final String BROKER_URL = "tcp://localhost:61616";
    private JsonParser mockedParser;

    @Before
    public void initialize() {
        mockedParser = Mockito.mock(JsonParser.class);
    }

    @Test()
    public void hasNextShouldReturnFalseWithEmptyCollection() throws IOException, JMSException {
        Mockito.when(mockedParser.hasNext()).thenReturn(false);
        Iterator<Employee> employeeIterator = new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_FILE_PATH, BROKER_URL)
                .createEmployeeIterator();
        Assert.assertEquals(false, employeeIterator.hasNext());
    }

    @Test
    public void hasNextAndNextShouldWorkCorrectly() throws IOException, JMSException {
        Iterator<Employee> employeeIterator = new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_FILE_PATH, BROKER_URL)
                .createEmployeeIterator();
        Assert.assertEquals(true, employeeIterator.hasNext());
        employeeIterator.next();
        Assert.assertEquals(true, employeeIterator.hasNext());
        employeeIterator.next();
        Assert.assertEquals(true, employeeIterator.hasNext());
        employeeIterator.next();
        Assert.assertEquals(true, employeeIterator.hasNext());
        employeeIterator.next();
        Assert.assertEquals(false, employeeIterator.hasNext());
    }
}
