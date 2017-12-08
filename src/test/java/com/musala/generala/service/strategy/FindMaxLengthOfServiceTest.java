package com.musala.generala.service.strategy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.musala.generala.PredefinedEmployeeTestSubjects.*;

class FindMaxLengthOfServiceTest {
    private static final double DELTA = 1e-15;
    private Strategy strategy = new FindMaxLengthOfService();

    @Test
    void addEmployeeShouldWorkCorrectly() throws NoSuchFieldException, IllegalAccessException {
        strategy.addEmployee(NORMAN);
        Field maxLengthOfServiceField = strategy.getClass().getDeclaredField("maxLengthOfService");
        maxLengthOfServiceField.setAccessible(true);
        Assert.assertEquals(10.1, maxLengthOfServiceField.getDouble(strategy), DELTA);
    }

    @Test
    void returnResultShouldWorkCorrectlyWithNormalData() {
        strategy.addEmployee(NORMAN);
        strategy.addEmployee(NORBERT);
        strategy.addEmployee(NORA);
        Assert.assertEquals(30.3, strategy.returnResult(), DELTA);
    }

    @Test
    void returnResultShouldWorkCorrectlyWithCornerCaseMaxData() {
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        Assert.assertEquals(Double.MAX_VALUE, strategy.returnResult(), DELTA);
    }
}