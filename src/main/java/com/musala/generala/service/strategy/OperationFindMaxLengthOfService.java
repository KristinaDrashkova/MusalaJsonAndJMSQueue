package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

public class OperationFindMaxLengthOfService implements IStrategy {
    private double maxLengthOfService = 0;

    @Override
    @SuppressWarnings("unchecked")
    public Double doOperation(Employee... employee) {
        if (employee.length > 0) {
            if (employee[0].getLengthOfService() > maxLengthOfService) {
                maxLengthOfService = employee[0].getLengthOfService();
            }
        }
        return maxLengthOfService;
    }
}
