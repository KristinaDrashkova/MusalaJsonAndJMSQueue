package com.musala.generala.service.iterator;

import com.musala.generala.models.Employee;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.Reader;
import java.util.*;

class EmployeeIterator implements Iterator<Employee> {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeIterator.class);
    private Employee cachedEmployee;
    private boolean isFinished = false;
    private String name;
    private int age;
    private double lengthOfService;
    private Map<String, String> applicationPropertiesData;
    private String nameLabel = "name";
    private String ageLabel = "age";
    private String lengthOfServiceLabel = "lengthOfService";
    private JsonParser parser;
    private Reader reader;

    EmployeeIterator(Reader reader, Map<String, String> applicationPropertiesData) {
        this.applicationPropertiesData = applicationPropertiesData;
        this.reader = reader;
        this.parser = Json.createParser(reader);
        if (this.applicationPropertiesData.size() > 0) {
            this.nameLabel = getNameLabel();
            this.ageLabel = getAgeLabel();
            this.lengthOfServiceLabel = getLengthOfServiceLabel();
        }
    }

    @Override
    public boolean hasNext() {
        if (this.cachedEmployee != null) {
            return true;
        } else if (this.isFinished) {
            return false;
        }
        parseEmployee();
        return !(this.isFinished && this.cachedEmployee == null);
    }

    @Override
    public Employee next() {
        Employee currentEmployee = null;
        if (hasNext()) {
            currentEmployee = this.cachedEmployee;
            this.cachedEmployee = null;
        }
        return currentEmployee;
    }

    private void parseEmployee() {
        JsonParser.Event event;
        while (this.parser.hasNext() && (event = this.parser.next()) != JsonParser.Event.END_OBJECT) {
            if (event == JsonParser.Event.END_ARRAY) {
                close();
                return;
            }
            if (event == JsonParser.Event.KEY_NAME) {
                String key = this.parser.getString();
                if (key.equals(this.nameLabel)) {
                    this.parser.next();
                    this.name = this.parser.getString();
                } else if (key.equals(this.ageLabel)) {
                    this.parser.next();
                    this.age = this.parser.getInt();
                } else if (key.equals(this.lengthOfServiceLabel)) {
                    this.parser.next();
                    this.lengthOfService = Double.parseDouble(this.parser.getString());
                }
            }
        }
        try {
            if (isEmployee()) {
                this.cachedEmployee = new Employee(this.name, this.age, this.lengthOfService);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
    }


    private boolean isEmployee() {
        return !(StringUtils.isBlank(this.name) && this.age == 0 && this.lengthOfService == 0.0);
    }

    private void close() {
        this.isFinished = true;
        this.cachedEmployee = null;
        IOUtils.closeQuietly(this.reader);
    }

    private String getNameLabel() {
        return this.applicationPropertiesData.get("nameLabel");
    }

    private String getAgeLabel() {
        return this.applicationPropertiesData.get("ageLabel");
    }

    private String getLengthOfServiceLabel() {
        return this.applicationPropertiesData.get("lengthOfServiceLabel");
    }
}
