package com.cs.ku.log.service;

public interface EmailService {

    void send(String to, String subject, String body);

}
