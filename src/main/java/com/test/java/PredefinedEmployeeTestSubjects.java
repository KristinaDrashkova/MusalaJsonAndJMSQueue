package com.test.java;

import com.musala.generala.models.Employee;

public class PredefinedEmployeeTestSubjects {
    static final String CONFIG_FILENAME_TEST_PATH = "src/main/java/com/test/WEB-INF/lib/log4j.properties";
    static Employee NORMAN = new Employee("Aaaaaaf", 10, 10.1);
    static Employee NORA = new Employee("Aaaaaaf Bbbbbf", 20, 20.2);
    static Employee NORBERT = new Employee("Aaaaaaf Bbbbbf Cccccf", 30, 30.3);
    static Employee MAXIMILIAN = new Employee("A B C", Integer.MAX_VALUE, Double.MAX_VALUE);
}
