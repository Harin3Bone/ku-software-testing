package com.cs.ku.shape;

public class Rectangle {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getArea() {
        return length * width;
    }

    public void changeSize(int factor) {
        this.length += factor;
        this.width += factor;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}
