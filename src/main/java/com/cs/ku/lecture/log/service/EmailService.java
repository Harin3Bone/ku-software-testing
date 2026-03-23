package com.cs.ku.lecture.log.service;

public interface EmailService {

    void send(String to, String subject, String body);

}
