package com.cs.ku.lecture.registration;

public interface PasswordEncoder {

    String hash(String password);

}
