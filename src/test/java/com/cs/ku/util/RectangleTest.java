package com.cs.ku.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    @Test
    void testGetArea() {
        // given
        var rectangle = new Rectangle(5.0, 3.0);

        // when
        var actual = rectangle.getArea();

        // then
        assertEquals(15.0, actual);
    }

    @Test
    void testGetAreaWithDelta() {
        // given
        var rectangle = new Rectangle(5.0, 4.9999);

        // when
        var actual = rectangle.getArea();
        assertEquals(25.0, actual, 0.01);
    }

    @Test
    void testChangeSize() {
        // given
        var rectangle = new Rectangle(2.0, 3.0);

        // when
        rectangle.changeSize(10);

        // then
        assertEquals(12.0, rectangle.getLength());
        assertEquals(13.0, rectangle.getWidth());
    }

}
