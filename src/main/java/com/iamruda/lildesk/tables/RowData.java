package com.iamruda.lildesk.tables;

import java.util.HashMap;
import java.util.Map;

public class RowData {
    private Map<String, Object> fieldValues;
    private Map<String, Integer> fieldTypes;

    public RowData() {
        fieldValues = new HashMap<>();
        fieldTypes = new HashMap<>();
    }

    public void addField(String fieldName, Object value, int type) {
        fieldValues.put(fieldName, value);
        fieldTypes.put(fieldName, type);
    }

    public Object getValue(String fieldName) {
        return fieldValues.get(fieldName);
    }

    public int getType(String fieldName) {
        return fieldTypes.get(fieldName);
    }
}
