package com.example;

import java.util.Map;

public class NestedKeyFinder {
    public static Object getValue(Map<String, Object> objectMap, String keyPathToSearch) {

        System.out.printf("Nested object: %s, key path: %s\n", objectMap, keyPathToSearch);

        if (objectMap == null || keyPathToSearch == null || keyPathToSearch.isEmpty()) {
            throw new IllegalArgumentException("Input map and key path must not be null or empty.");
        }

        String[] keys = keyPathToSearch.trim().split("/")    ;
        Object currentValue = objectMap;

        for(String key: keys) {
            if (currentValue instanceof Map) {
                Map<String, Object> currentMap = (Map<String, Object>) currentValue;
                if (currentMap.containsKey(key)) {
                    currentValue = currentMap.get(key);
                } else {
                    System.out.printf("Key '%s' not found in current map .%n", key);
                    return null;
                }
            } else {
                System.out.printf("Found non-map object before reaching final key: '%s'%n", key);
                return null;
            }
        }
        return currentValue;
    }
}
