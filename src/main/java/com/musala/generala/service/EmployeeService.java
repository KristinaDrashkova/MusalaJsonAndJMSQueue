package com.musala.generala.service;

import com.musala.generala.exeptions.NoEmployeesException;
import com.musala.generala.models.Employee;
import com.musala.generala.service.iterator.EmployeeIteratorFactory;
import com.musala.generala.service.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class EmployeeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private EmployeeIteratorFactory employeeIteratorFactory;

    public EmployeeService(EmployeeIteratorFactory employeeIteratorFactory) {
        this.employeeIteratorFactory = employeeIteratorFactory;
    }

    public void logEmployeeInfo() throws IOException, NoEmployeesException {
        Strategy[] strategies = new Strategy[] {new CalculateAverageAge(), new CalculateAverageLengthOfService()
                , new FindMaxLengthOfService(), new FindMostCommonCharacters(3)};
        addEmployees(strategies);
        for (Strategy strategy : strategies) {
            LOGGER.info(strategy.getName()  + ": " + strategy.returnResult());
        }
    }

    private void addEmployees(Strategy... strategies) throws IOException, NoEmployeesException {
        Iterator<Employee> employeeIterator = this.employeeIteratorFactory.createEmployeeIterator();
        if (!employeeIterator.hasNext()) {
            throw new NoEmployeesException("There are no employees");
        }
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            for (Strategy strategy: strategies) {
                strategy.addEmployee(employee);
            }
        }
    }

}
