package com.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NestedKeyFinderTest {

    @Test
    /** Verifies that a valid nested key path returns the correct value. */
    void testHappyPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertEquals("value1", NestedKeyFinder.getValue(map, "a/b/c"));
    }

    @Test
    /** Verifies null is returned when a key in the path does not exist. */
    void testKeyDoesNotExist() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertNull(NestedKeyFinder.getValue(map, "a/x/c"));
    }

    @Test
    /** Verifies that a null key path throws an IllegalArgumentException. */
    void testNullKeyPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertThrows(IllegalArgumentException.class, () -> NestedKeyFinder.getValue(map, null));
    }

    @Test
    /** Verifies that a null map throws an IllegalArgumentException. */
    void testNullMap() {
        assertThrows(IllegalArgumentException.class, () -> NestedKeyFinder.getValue(null, "a/b"));
    }

    @Test
    /** Verifies that an empty key path throws an IllegalArgumentException. */
    void testEmptyKeyPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", Map.of("c", "value1")));
        assertThrows(IllegalArgumentException.class, () -> NestedKeyFinder.getValue(map, ""));
    }

    @Test
    /** Verifies null is returned when a non-map object is encountered mid-path. */
    void testIntermediateValueNotMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "notAMap");
        assertNull(NestedKeyFinder.getValue(map, "a/b"));
    }

    @Test
    /** Verifies that a top-level key returns its correct value. */
    void testSingleKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value2");
        assertEquals("value2", NestedKeyFinder.getValue(map, "key"));
    }

    @Test
    /** Ensures null is returned when the key path is broken. */
    void testEmptySegmentInPath() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", Map.of("b", "c"));
        assertNull(NestedKeyFinder.getValue(map, "a//b"));
    }
}
