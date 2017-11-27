package com.musala.generala;

import com.musala.generala.exeptions.NoEmployeesException;
import com.musala.generala.service.iterator.EmployeeIteratorFactoryFromFile;
import com.musala.generala.service.IEmployeeService;
import com.musala.generala.service.EmployeeService;
import com.musala.generala.service.iterator.IEmployeeIteratorFactory;

import java.io.IOException;


public class Main {
    //    private static final String EMPTY_FILE = "C:\\Users\\kristina.drashkova\\IdeaProjects\\untitled\\src\\test\\resources\\empty  file.txt";
    private static final String RESOURCES_EMPLOYEE_DATA_PATH = "src/main/resources/employee data.txt";

    public static void main(String[] args) throws NoEmployeesException, IOException {
        IEmployeeIteratorFactory employeeIteratorFactory =
                new EmployeeIteratorFactoryFromFile(RESOURCES_EMPLOYEE_DATA_PATH);
        IEmployeeService employeeService = new EmployeeService(employeeIteratorFactory);
        employeeService.logEmployeeInfo();
    }
}
