package com.musala.generala.service.iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.jms.JMSException;
import javax.json.stream.JsonParsingException;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;


public class EmployeeIteratorTest {
    private static final String FILE_NAME = "src/test/resources/employees.json";
    private static final String EMPTY_FILE_NAME = "src/test/resources/employees empty file.json";
    private EmployeeIteratorFactory mockedEmployeeIteratorFactory;
    private Iterator employeeIterator;
    private Reader reader;

    @Before
    public void initialize() throws IOException {
        reader = new BufferedReader(new FileReader(FILE_NAME));
        mockedEmployeeIteratorFactory = Mockito.mock(EmployeeIteratorFactoryFromQueue.class);
        Mockito.when(mockedEmployeeIteratorFactory
                .createEmployeeIterator()).thenReturn(new EmployeeIterator(reader, new HashMap<>()));
    }

    @Test(expected = JsonParsingException.class)
    public void shouldThrowExceptionWithEmptyCollection() throws IOException, JMSException {
        reader = new BufferedReader(new FileReader(EMPTY_FILE_NAME));
        Mockito.when(mockedEmployeeIteratorFactory
                .createEmployeeIterator()).thenReturn(new EmployeeIterator(reader, new HashMap<>()));
        employeeIterator = mockedEmployeeIteratorFactory.createEmployeeIterator();
        Assert.assertEquals(false, employeeIterator.hasNext());
    }

    @Test
    public void hasNextAndNextShouldWorkCorrectly() throws IOException, JMSException {
        employeeIterator = mockedEmployeeIteratorFactory.createEmployeeIterator();
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
