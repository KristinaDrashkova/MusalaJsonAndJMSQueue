package com.musala.generala;

import com.musala.generala.exeptions.NoEmployeesException;
import com.musala.generala.service.iterator.EmployeeIteratorFactoryFromQueue;
import com.musala.generala.service.IEmployeeService;
import com.musala.generala.service.EmployeeService;
import com.musala.generala.service.iterator.IEmployeeIteratorFactory;

import javax.jms.JMSException;
import java.io.IOException;


public class Main {
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "src/main/resources/application.properties";
    private static final String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) throws NoEmployeesException, IOException, JMSException {
        IEmployeeIteratorFactory employeeIteratorFactory =
                new EmployeeIteratorFactoryFromQueue(APPLICATION_PROPERTIES_FILE_PATH, BROKER_URL);
        IEmployeeService employeeService = new EmployeeService(employeeIteratorFactory);
        employeeService.logEmployeeInfo();
    }
}
