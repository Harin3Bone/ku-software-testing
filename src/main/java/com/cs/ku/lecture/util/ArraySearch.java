package com.cs.ku.lecture.util;

public class ArraySearch {

    public int findFirst(int[] array, int x) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == x) return i;
        }
        return -1;
    }

}
