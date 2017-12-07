package com.musala.generala.service.strategy;

import com.musala.generala.models.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindMostCommonCharacters implements Strategy {
    private Map<Character, Integer> characterIntegerMap;
    private int count;

    public FindMostCommonCharacters(int count) {
        this.characterIntegerMap = new HashMap<>();
        this.count = count;
    }

    /**
     * Adds to collection all the characters
     * in the names of the employee
     * with the number of their occurrences as a value
     */
    @Override
    public void addEmployee(Employee employee) {
        for (char ch : employee.getName().toCharArray()) {
            if (!characterIntegerMap.containsKey(ch)) {
                characterIntegerMap.put(ch, 0);
            }
            int value = characterIntegerMap.get(ch);
            characterIntegerMap.put(ch, value + 1);
        }
    }

    @Override
    public String getName() {
        return "First " + count + " most common characters: ";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Character> returnResult() {
        return mostCommonCharactersInEmployeesNames();
    }

    /**
     * Returns list of the first @count most common characters
     * from all the names of all the employee names
     *
     * @return list of the first @count most common characters
     * from all the names
     */
    private List<Character> mostCommonCharactersInEmployeesNames() {
        if (count > characterIntegerMap.size()) {
            count = characterIntegerMap.size();
        }
        return characterIntegerMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(count).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
