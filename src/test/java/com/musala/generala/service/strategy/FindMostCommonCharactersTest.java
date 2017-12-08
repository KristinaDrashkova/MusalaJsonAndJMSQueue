package com.musala.generala.service.strategy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import static com.musala.generala.PredefinedEmployeeTestSubjects.*;

class FindMostCommonCharactersTest {
    private int count = 3;
    private Strategy strategy = new FindMostCommonCharacters(count);

    @Test
    @SuppressWarnings("unchecked")
    void addEmployeeShouldWorkCorrectly() throws NoSuchFieldException, IllegalAccessException {
        strategy.addEmployee(NORMAN);
        Field characterIntegerMapField = strategy.getClass().getDeclaredField("characterIntegerMap");
        characterIntegerMapField.setAccessible(true);
        Map<Character, Integer> characterIntegerMap = (Map<Character, Integer>) characterIntegerMapField.get(strategy);
        Assert.assertTrue(5 == characterIntegerMap.get('a'));
        Assert.assertTrue(1 == characterIntegerMap.get('A'));
        Assert.assertTrue(1 == characterIntegerMap.get('f'));
    }

    @Test
    void getNameShouldWorkCorrectly() {
        Assert.assertEquals("First " + count + " most common characters: ", strategy.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void returnResultShouldWorkCorrectly() {
        strategy.addEmployee(NORMAN);
        strategy.addEmployee(NORBERT);
        strategy.addEmployee(NORA);
        Character[] arr = ((ArrayList<Character>) this.strategy.returnResult()).toArray(new Character[3]);
        Assert.assertArrayEquals(new Character[]{'a', 'b', 'f'}, arr);
    }

    @Test
    @SuppressWarnings("unchecked")
    void returnResultShouldWorkCorrectlyWithLessThanCountCharacters() {
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        strategy.addEmployee(MAXIMILIAN);
        Character[] arr = ((ArrayList<Character>) this.strategy.returnResult()).toArray(new Character[2]);
        Assert.assertArrayEquals(new Character[]{'A', 'B'}, arr);
    }

    @Test
    @SuppressWarnings("unchecked")
    void returnResultShouldWorkCorrectlyWithoutAnyInput() {
        Character[] arr = ((ArrayList<Character>) this.strategy.returnResult()).toArray(new Character[0]);
        Assert.assertArrayEquals(new Character[]{}, arr);
    }
}