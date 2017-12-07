package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

public interface Strategy {
    void addEmployee(Employee employee);

    <T> T returnResult();

    String getName();
}
