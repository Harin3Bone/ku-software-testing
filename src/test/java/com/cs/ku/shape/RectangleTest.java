package com.cs.ku.shape;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class RectangleTest {

    private Rectangle rectangle;

    @BeforeAll
    static void beforeAll() {
        log.info("Before all tests.");
    }

    @BeforeEach
    void setUp() {
        log.info("Setup.");
        rectangle = new Rectangle(4.0, 5.0);
    }

    @AfterEach
    void tearDown() {
        log.info("Tear down.");
    }

    @AfterAll
    static void afterAll() {
        log.info("After all tests.");
    }

    @Test
    void testGetArea() {
        log.info("Test getArea.");

        // when
        var actual = rectangle.getArea();

        // then
        assertEquals(20, actual);
    }

    @Test
    void testGetAreaWithDelta() {
        log.info("Test Get Area with Delta.");

        // when
        var actual = rectangle.getArea();

        // then
        assertEquals(20.0, actual, 0.01);
    }

    @Test
    void testChangeSize() {
        log.info("Test Change Size.");

        // when
        rectangle.changeSize(10);

        // then
        assertEquals(14.0, rectangle.getLength());
        assertEquals(15.0, rectangle.getWidth());
    }

}
