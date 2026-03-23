package com.cs.ku.lecture.util;

public class EmailApp {

    public boolean register(String email, String password, int age) {
        return email.indexOf("@") >= 1 && password.length() >= 8 && age >= 18;
    }

}