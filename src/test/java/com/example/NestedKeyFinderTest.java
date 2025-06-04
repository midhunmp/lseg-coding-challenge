package com.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NestedKeyFinderTest {

    @Test
    void testHappyPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertEquals("value1", NestedKeyFinder.getValue(map, "a/b/c"));
    }

    @Test
    void testKeyDoesNotExist() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertNull(NestedKeyFinder.getValue(map, "a/x/c"));
    }

    @Test
    void testNullKeyPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertThrows(IllegalArgumentException.class, () -> NestedKeyFinder.getValue(map, null));
    }

    @Test
    void testNullMap() {
        assertThrows(IllegalArgumentException.class, () -> NestedKeyFinder.getValue(null, "a/b"));
    }

    @Test
    void testEmptyKeyPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertThrows(IllegalArgumentException.class, () -> NestedKeyFinder.getValue(map, ""));
    }

    @Test
    void testIntermediateValueNotMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "notAMap");
        assertNull(NestedKeyFinder.getValue(map, "a/b"));
    }

    @Test
    void testSingleKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value2");
        assertEquals("value2", NestedKeyFinder.getValue(map, "key"));
    }

    @Test
    void testEmptySegmentInPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", "c"));
        assertNull(NestedKeyFinder.getValue(map, "a//b"));
    }
}
