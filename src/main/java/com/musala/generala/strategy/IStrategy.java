package com.musala.generala.strategy;

import com.musala.generala.models.Employee;

public interface IStrategy {
    <T>T doOperation(Employee... employee);
}
