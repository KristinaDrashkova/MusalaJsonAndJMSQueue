package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

public class OperationCalculateAverageLengthOfService implements IStrategy {
    private double lengthOfService;

    @Override
    @SuppressWarnings("unchecked")
    public Double doOperation(Employee... employee) {
        if (employee.length > 0) {
            lengthOfService += employee[0].getLengthOfService();
        }
        return lengthOfService;
    }
}
