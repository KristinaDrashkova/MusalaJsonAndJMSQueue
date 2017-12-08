package com.musala.generala.service.iterator;

import org.junit.Test;

import java.io.IOException;


public class EmployeeIteratorFactoryTest {
    private static final String APPLICATION_PROPERTIES_INVALID_FILE_PATH = "src/resources/application.properties";
    private static final String BROKER_URL = "tcp://localhost:61616";

    @Test(expected = IOException.class)
    public void getEmployeeIteratorShouldThrowExceptionWithInvalidPath() throws Exception {
        EmployeeIteratorFactory employeeIteratorFactory = new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_INVALID_FILE_PATH, BROKER_URL);
        employeeIteratorFactory.createEmployeeIterator();
    }
}