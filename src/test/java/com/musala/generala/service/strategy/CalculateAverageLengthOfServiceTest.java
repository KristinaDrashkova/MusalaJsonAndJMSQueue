package com.musala.generala.service.strategy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.musala.generala.PredefinedEmployeeTestSubjects.*;

class CalculateAverageLengthOfServiceTest {
    private static final double DELTA = 1e-15;
    private Strategy strategy = new CalculateAverageLengthOfService();

    @Test
    void addEmployeeShouldWorkCorrectly() throws NoSuchFieldException, IllegalAccessException {
        strategy.addEmployee(NORMAN);
        Field lengthOfServiceField = strategy.getClass().getDeclaredField("lengthOfService");
        lengthOfServiceField.setAccessible(true);
        Field counterField = strategy.getClass().getDeclaredField("counter");
        counterField.setAccessible(true);
        Assert.assertEquals(10.1, lengthOfServiceField.getDouble(strategy), DELTA);
        Assert.assertEquals(1, counterField.getDouble(strategy), DELTA);
    }

    @Test
    void getNameShouldWorkCorrectly() {
        Assert.assertEquals("Average length of service of the employees: ", strategy.getName());
    }

    @Test
    void returnResultShouldWorkCorrectlyWithNormalData() {
        strategy.addEmployee(NORMAN);
        strategy.addEmployee(NORBERT);
        strategy.addEmployee(NORA);
        Assert.assertEquals(20.2, strategy.returnResult(), DELTA);
    }

    @Test
    void returnResultShouldWorkCorrectlyWithCornerCaseMaxData() {
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        Assert.assertEquals(Double.POSITIVE_INFINITY, strategy.returnResult(), DELTA);
    }
}