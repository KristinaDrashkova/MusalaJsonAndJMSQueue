package com.musala.generala.service;

import com.musala.generala.exeptions.NoEmployeesException;
import com.musala.generala.models.Employee;
import com.musala.generala.service.iterator.EmployeeIteratorFactory;
import com.musala.generala.service.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class ImplEmployeeService implements EmployeeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImplEmployeeService.class);
    private EmployeeIteratorFactory employeeIteratorFactory;
    private List<Strategy> strategies;

    public ImplEmployeeService(EmployeeIteratorFactory employeeIteratorFactory) {
        int count = 3;
        this.employeeIteratorFactory = employeeIteratorFactory;
        strategies = Arrays.asList(new CalculateAverageAge(), new CalculateAverageLengthOfService()
                , new FindMaxLengthOfService(), new FindMostCommonCharacters(count));
    }

    @Override
    public void logEmployeeInfo() throws IOException, NoEmployeesException {
        addEmployees();
        for (Strategy strategy : strategies) {
            LOGGER.info(strategy.getName() + strategy.returnResult());
        }
    }

    private boolean validateEmployeeIterator(Iterator<Employee> employeeIterator) {
        return employeeIterator.hasNext();
    }

    private void addEmployees() throws IOException, NoEmployeesException {
        Iterator<Employee> employeeIterator = this.employeeIteratorFactory.createEmployeeIterator();
        if (!validateEmployeeIterator(employeeIterator)) {
            throw new NoEmployeesException("There are no employees");
        }
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            strategies.forEach(s -> s.addEmployee(employee));
        }
    }

}
