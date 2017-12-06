package com.musala.generala.service;

import com.musala.generala.exeptions.NoEmployeesException;
import com.musala.generala.models.Employee;
import com.musala.generala.service.iterator.IEmployeeIteratorFactory;
import com.musala.generala.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService implements IEmployeeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private IEmployeeIteratorFactory employeeIteratorFactory;
    private IStrategy operationCalculateAverageAge;
    private IStrategy operationCalculateAverageLengthOfService;
    private IStrategy operationFindMaxLengthOfService;
    private IStrategy operationFindMostCommonCharacters;
    private double counter = 0.0;

    public EmployeeService(IEmployeeIteratorFactory employeeIteratorFactory) {
        this.employeeIteratorFactory = employeeIteratorFactory;
        this.operationCalculateAverageAge = new OperationCalculateAverageAge();
        this.operationCalculateAverageLengthOfService = new OperationCalculateAverageLengthOfService();
        this.operationFindMaxLengthOfService = new OperationFindMaxLengthOfService();
        this.operationFindMostCommonCharacters = new OperationFindMostCommonCharacters();
    }

    @Override
    public void logEmployeeInfo() throws IOException, NoEmployeesException {
        Iterator employeeIterator = this.employeeIteratorFactory.createEmployeeIterator();
        if (!employeeIterator.hasNext()) {
            LOGGER.error("There are no employees");
            throw new NoEmployeesException("There are no employees");
        } else {
            iterate();
            double averageAgeOfEmployees = averageAgeOfEmployees();
            double averageLengthOfServiceOfEmployees = averageLengthOfServiceOfEmployees();
            List<Character> mostCommonCharactersInEmployeesNames = mostCommonCharactersInEmployeesNames(3);
            double maximumLengthOfServiceOfEmployee = maximumLengthOfServiceOfEmployee();
            LOGGER.info("Average age of employees: {}", averageAgeOfEmployees);
            LOGGER.info("First three most common characters: {}", mostCommonCharactersInEmployeesNames.toString());
            LOGGER.info("Average length of service of the employees: {}", averageLengthOfServiceOfEmployees);
            LOGGER.info("Maximum length of service among all employees: {}", maximumLengthOfServiceOfEmployee);
        }
    }

    /**
     * Returns the calculated average age
     * from all the employees
     *
     * @return calculated average age
     */
    @Override
    public double averageAgeOfEmployees() throws IOException, NoEmployeesException {
        return (Integer) operationCalculateAverageAge.doOperation() / counter;
    }

    /**
     * Returns calculated average length of service
     * from all the employees
     *
     * @return calculated average length of service
     */
    @Override
    public double averageLengthOfServiceOfEmployees() throws IOException, NoEmployeesException {
        return (Double) operationCalculateAverageLengthOfService.doOperation() / counter;
    }

    /**
     * Returns the maximum length of service
     * from all the employees
     *
     * @return the maximum length of service
     */
    @Override
    public double maximumLengthOfServiceOfEmployee() throws IOException {
        return (Double) operationFindMaxLengthOfService.doOperation();
    }

    /**
     * Returns list of the first three most common characters
     * from all the names of all the employee names
     *
     * @return list of the first three most common characters
     * from all the names
     */
    @Override
    public List<Character> mostCommonCharactersInEmployeesNames(int count) throws IOException {
        Map<Character, Integer> characterIntegerMap = operationFindMostCommonCharacters.doOperation();
        if (count > characterIntegerMap.size()) {
            count = characterIntegerMap.size();
        }
        return characterIntegerMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(count).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * Returns collection from all the characters
     * in the names of the all employees
     * with the number of their occurrences as a value
     *
     * @return collection with character for a key and its occurrence
     * in all the names of all employees as a value
     */

    private boolean validateEmployeeIterator(Iterator<Employee> employeeIterator) {
        return employeeIterator.hasNext();
    }


    private void iterate() throws IOException, NoEmployeesException {
        Iterator<Employee> employeeIterator = this.employeeIteratorFactory.createEmployeeIterator();
        if (!validateEmployeeIterator(employeeIterator)) {
            throw new NoEmployeesException("There are no employees");
        }
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            counter++;
            operationCalculateAverageAge.doOperation(employee);
            operationCalculateAverageLengthOfService.doOperation(employee);
            operationFindMaxLengthOfService.doOperation(employee);
            operationFindMostCommonCharacters.doOperation(employee);
        }
    }

}
