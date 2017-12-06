package com.musala.generala.strategy;

import com.musala.generala.models.Employee;

import java.util.HashMap;
import java.util.Map;

public class OperationFindMostCommonCharacters implements IStrategy {
    private Map<Character, Integer> characterIntegerMap;
    @Override
    @SuppressWarnings("unchecked")
    public Map<Character, Integer> doOperation(Employee... employee) {
        if (characterIntegerMap == null) {
            characterIntegerMap = new HashMap<>();
        }
        if (employee.length > 0) {
            for (char ch : employee[0].getName().toCharArray()) {
                if (!characterIntegerMap.containsKey(ch)) {
                    characterIntegerMap.put(ch, 0);
                }
                int value = characterIntegerMap.get(ch);
                characterIntegerMap.put(ch, value + 1);
            }
        }
        return characterIntegerMap;
    }
}
