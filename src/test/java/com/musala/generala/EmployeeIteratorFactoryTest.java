package com.musala.generala;

import com.musala.generala.service.iterator.EmployeeIteratorFactoryFromQueue;
import com.musala.generala.service.iterator.EmployeeIteratorFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;


public class EmployeeIteratorFactoryTest {
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "src/main/resources/application.properties";
    private static final String BROKER_URL = "tcp://localhost:61616";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = IOException.class)
    public void getEmployeeIteratorShouldThrowExceptionWithInvalidPath() throws Exception {
        EmployeeIteratorFactory employeeIteratorFactory = new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_FILE_PATH, BROKER_URL);
        employeeIteratorFactory.createEmployeeIterator();
        this.thrown.expect(IOException.class);
        this.thrown.reportMissingExceptionWithMessage("Exception expected");
        this.thrown.expectMessage("Could not find file ");
    }
}