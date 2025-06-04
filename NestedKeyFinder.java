
import java.util.Map;
import java.util.HashMap;

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
    static void printResult(String testName, Map<String, Object> map, String keyPath) {
        try {
            Object result = getValue(map, keyPath);
            System.out.println(testName + " Result: " + result);
        } catch (Exception e) {
            System.out.println(testName + " Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Test 1: Happy path
        Map<String, Object> map1 = new HashMap<>();
        map1.put("a", Map.of("b", Map.of("c", "value1")));
        printResult("Test 1", map1, "a/b/c"); // Expected: value1

        // Test 2: Key does not exist
        printResult("Test 2", map1, "a/x/c"); // Expected: null

        // Test 3: Key path is null
        printResult("Test 3", map1, null); // Expected: exception

        // Test 4: Map is null
        printResult("Test 4", null, "a/b"); // Expected: exception

        // Test 5: Key path is empty
        printResult("Test 5", map1, ""); // Expected: exception

        // Test 6: Intermediate value is not a map
        Map<String, Object> map2 = new HashMap<>();
        map2.put("a", "notAMap");
        printResult("Test 6", map2, "a/b"); // Expected: null

        // Test 7: one key value
        Map<String, Object> map3 = new HashMap<>();
        map3.put("key", "value2");
        printResult("Test 7", map3, "key"); // Expected: value2

        // Test 8: Key path with breaks
        Map<String,Object>map4 = new HashMap<>();
        printResult("Test 8",map1,"a//b"); //Expected: null
    }

}
