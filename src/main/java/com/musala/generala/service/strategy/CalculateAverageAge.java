package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

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
        return "Average age of employees: ";
    }

    /**
     * Returns the calculated average age
     * from all the employees
     *
     * @return calculated average age
     */
    @SuppressWarnings("unchecked")
    @Override
    public Double returnResult() {
        return age / counter;
    }
}
