package com.musala.generala.service.iterator;

import com.musala.generala.models.Employee;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;
import java.io.Reader;
import java.util.*;

class EmployeeIterator implements Iterator<Employee> {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeIterator.class);
    private Employee cachedEmployee;
    private boolean isFinished;
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
        if (cachedEmployee != null) {
            return true;
        } else if (isFinished) {
            return false;
        }
        parseEmployee();
        return !(isFinished && cachedEmployee == null);
    }

    @Override
    public Employee next() {
        Employee currentEmployee = null;
        if (hasNext()) {
            currentEmployee = cachedEmployee;
            cachedEmployee = null;
        }
        return currentEmployee;
    }

    private void parseEmployee() {
        try {
            JsonParser.Event event;
            while (parser.hasNext() && (event = parser.next()) != JsonParser.Event.END_OBJECT) {
                if (event == JsonParser.Event.END_ARRAY) {
                    close();
                    return;
                }
                if (event == JsonParser.Event.KEY_NAME) {
                    String key = parser.getString();
                    if (key.equals(nameLabel)) {
                        parser.next();
                        name = parser.getString();
                    } else if (key.equals(ageLabel)) {
                        parser.next();
                        age = parser.getInt();
                    } else if (key.equals(lengthOfServiceLabel)) {
                        parser.next();
                        lengthOfService = Double.parseDouble(parser.getString());
                    }
                }
            }
        } catch (JsonParsingException e) {
            LOGGER.error("There was a problem with parsing");
            throw e;
        }
        try {
            if (isEmployee()) {
                cachedEmployee = new Employee(name, age, lengthOfService);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private boolean isEmployee() {
        return !(StringUtils.isBlank(name) && age == 0 && lengthOfService == 0.0);
    }

    private void close() {
        isFinished = true;
        cachedEmployee = null;
        IOUtils.closeQuietly(reader);
    }

    private String getNameLabel() {
        return applicationPropertiesData.get("nameLabel");
    }

    private String getAgeLabel() {
        return applicationPropertiesData.get("ageLabel");
    }

    private String getLengthOfServiceLabel() {
        return applicationPropertiesData.get("lengthOfServiceLabel");
    }
}
