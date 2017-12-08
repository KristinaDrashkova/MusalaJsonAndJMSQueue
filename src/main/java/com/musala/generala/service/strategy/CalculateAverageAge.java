package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

/**
 * Returns the calculated average age
 * from all the employees
 */
public class CalculateAverageAge implements Strategy {
    private double age;
    private double counter;

    @Override
    public void addEmployee(Employee employee) {
        age += employee.getAge();
        counter++;
    }

    @Override
    public String getName() {
        return "Average age of employees";
    }

    @SuppressWarnings("unchecked")
    @Override
    public Double returnResult() {
        return age / counter;
    }
}
