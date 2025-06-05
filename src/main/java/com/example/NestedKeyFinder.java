package com.example;

import java.util.Map;

public class NestedKeyFinder {
    /**
     * Finds a value from a input nested map corresponding to the search keypath
     * using "/" as delimiter.
     *
     * @param inputMap        nested map to search
     * @param keyPathToSearch search keypath using "/" as delimiter
     * @return the value extracted at the specified nested key path, or null if the path is invalid or not found
     * @throws IllegalArgumentException if inputMap is null or keyPathToSearch is null/empty
     */
    public static Object getValue(Map<String, Object> inputMap, String keyPathToSearch) {

        System.out.println("Nested object: " + inputMap + ", key path: " + keyPathToSearch);

        if (inputMap == null || keyPathToSearch == null || keyPathToSearch.isEmpty()) {
            throw new IllegalArgumentException("Input map and key path must not be null or empty.");
        }

        String[] keys = keyPathToSearch.trim().split("/");
        Object currentValue = inputMap;

        for (String key : keys) {
            if (currentValue instanceof Map) {
                Map<String, Object> currentMap = (Map<String, Object>) currentValue;
                if (currentMap.containsKey(key)) {
                    currentValue = currentMap.get(key);
                } else {
                    System.out.println("Key '" + key + "' not found in current map.");
                    return null;
                }
            } else {
                System.out.println("Found non-map object before reaching final key: '" + key + "'");
                return null;
            }
        }
        return currentValue;
    }
}
