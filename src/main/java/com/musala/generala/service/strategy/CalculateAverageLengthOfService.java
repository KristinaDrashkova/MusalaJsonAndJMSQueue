package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

/**
 * Returns calculated average length of service
 * from all the employees
 */
public class CalculateAverageLengthOfService implements Strategy {
    private double lengthOfService;
    private double counter;

    @Override
    public void addEmployee(Employee employee) {
        lengthOfService += employee.getLengthOfService();
        counter++;
    }

    @Override
    public String getName() {
        return "Average length of service of the employees";
    }

    @SuppressWarnings("unchecked")
    @Override
    public Double returnResult() {
        return lengthOfService / counter;
    }
}
