package com.cs.ku.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    private Rectangle rectangle;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests.");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setup.");
        rectangle = new Rectangle(4.0, 5.0);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tear down.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all tests.");
    }

    @Test
    void testGetArea() {
        System.out.println("Test getArea.");

        // when
        var actual = rectangle.getArea();

        // then
        assertEquals(20, actual);
    }

    @Test
    void testGetAreaWithDelta() {
        System.out.println("Test Get Area with Delta.");

        // when
        var actual = rectangle.getArea();

        // then
        assertEquals(20.0, actual, 0.01);
    }

    @Test
    void testChangeSize() {
        System.out.println("Test Change Size.");

        // when
        rectangle.changeSize(10);

        // then
        assertEquals(14.0, rectangle.getLength());
        assertEquals(15.0, rectangle.getWidth());
    }

}
