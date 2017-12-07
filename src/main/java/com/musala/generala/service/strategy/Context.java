package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public <T> T executeStrategy(Employee... employees) {
        return strategy.doOperation(employees);
    }
}
