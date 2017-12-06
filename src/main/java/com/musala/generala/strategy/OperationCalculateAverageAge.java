package com.musala.generala.strategy;

import com.musala.generala.models.Employee;

public class OperationCalculateAverageAge implements IStrategy {
    private int age;
    @Override
    @SuppressWarnings("unchecked")
    public Integer doOperation(Employee... employee) {
        if (employee.length > 0) {
            age += employee[0].getAge();
        }
        return age;
    }
}
