package com.musala.generala.service;

import com.musala.generala.exeptions.NoEmployeesException;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    /**
     * Logs the information about the employees
     */
    void logEmployeeInfo() throws IOException, NoEmployeesException;
}
