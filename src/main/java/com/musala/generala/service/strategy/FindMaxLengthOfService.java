package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;


/**
 * Returns the maximum length of service
 * from all the employees
 */
public class FindMaxLengthOfService implements Strategy {
    private double maxLengthOfService = 0;

    @Override
    public void addEmployee(Employee employee) {
        if (employee.getLengthOfService() > maxLengthOfService) {
            maxLengthOfService = employee.getLengthOfService();
        }
    }

    @Override
    public String getName() {
        return "Maximum length of service among all employees";
    }

    @SuppressWarnings("unchecked")
    @Override
    public Double returnResult() {
        return maxLengthOfService;
    }
}
