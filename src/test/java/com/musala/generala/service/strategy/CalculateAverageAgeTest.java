package com.musala.generala.service.strategy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.musala.generala.PredefinedEmployeeTestSubjects.*;

class CalculateAverageAgeTest {
    private static final double DELTA = 1e-15;
    private Strategy strategy = new CalculateAverageAge();

    @Test
    void addEmployeeShouldWorkCorrectly() throws NoSuchFieldException, IllegalAccessException {
        strategy.addEmployee(NORMAN);
        Field ageField = strategy.getClass().getDeclaredField("age");
        ageField.setAccessible(true);
        Field counterField = strategy.getClass().getDeclaredField("counter");
        counterField.setAccessible(true);
        Assert.assertEquals(10, ageField.getDouble(strategy), DELTA);
        Assert.assertEquals(1, counterField.getDouble(strategy), DELTA);
    }

    @Test
    void getNameShouldWorkCorrectly() {
        Assert.assertEquals("Average age of employees: ", strategy.getName());
    }

    @Test
    void returnResultShouldWorkCorrectlyWithNormalData() {
        strategy.addEmployee(NORMAN);
        strategy.addEmployee(NORBERT);
        strategy.addEmployee(NORA);
        Assert.assertEquals(20, strategy.returnResult(), DELTA);
    }

    @Test
    void returnResultShouldWorkCorrectlyWithCornerCaseMaxData() {
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        Assert.assertEquals(Integer.MAX_VALUE, strategy.returnResult(), DELTA);
    }
}