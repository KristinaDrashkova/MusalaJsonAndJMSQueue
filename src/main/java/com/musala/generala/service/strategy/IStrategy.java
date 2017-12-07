package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

public interface IStrategy {
    <T> T doOperation(Employee... employee);
}
