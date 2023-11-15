package com.emma.app.action;

import com.emma.app.model.EmployeeRole;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public class EmployeeTypeConverter implements Converter {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Class<T> type, Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            String stringValue = (String) value;
            try {
                return (T) EmployeeRole.valueOf(stringValue);
            } catch (IllegalArgumentException e) {
                throw new ConversionException("Invalid Employee type value: " + stringValue);
            }
        } else {
            throw new ConversionException("Value is not a String");
        }
    }
}

