package com.cs.ku.registration;

public interface PasswordEncoder {

    String hash(String password);

}
