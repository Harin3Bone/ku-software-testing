package com.cs.ku.util;

public class EmailApp {

    public boolean register(String email, String password, int age) {
        if (email.indexOf("@") >= 1 && password.length() >= 8 && age >= 18) {
            return true;
        }
        return false;
    }

}