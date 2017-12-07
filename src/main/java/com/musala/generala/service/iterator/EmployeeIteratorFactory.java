package com.musala.generala.service.iterator;

import com.musala.generala.models.Employee;

import java.io.IOException;
import java.util.Iterator;

public interface EmployeeIteratorFactory {
    Iterator<Employee> createEmployeeIterator() throws IOException;
}
