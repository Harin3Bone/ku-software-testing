package com.cs.ku.shape;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Rectangle {

    private double length;
    private double width;

    public double getArea() {
        return length * width;
    }

    public void changeSize(int factor) {
        this.length += factor;
        this.width += factor;
    }

}
