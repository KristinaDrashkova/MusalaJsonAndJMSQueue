package com.musala.generala;

import com.musala.generala.models.Employee;
import com.musala.generala.service.iterator.EmployeeIteratorFactoryFromQueue;
import org.junit.Assert;
import org.junit.Test;

import javax.jms.JMSException;
import java.io.IOException;
import java.util.Iterator;


public class EmployeeIteratorTest {
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "src/main/resources/application.properties";

    @Test()
    public void hasNextShouldReturnFalseWithEmptyCollection() throws IOException, JMSException {
        Iterator<Employee> employeeIterator = new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_FILE_PATH)
                .createEmployeeIterator();
        Assert.assertEquals(false, employeeIterator.hasNext());
    }

    @Test
    public void hasNextAndNextShouldWorkCorrectly() throws IOException, JMSException {
        Iterator<Employee> employeeIterator = new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_FILE_PATH)
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
