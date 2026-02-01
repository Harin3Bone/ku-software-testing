package com.cs.ku.log.analyzer;

import com.cs.ku.log.service.EmailService;
import com.cs.ku.log.service.LogService;

public class EmailLogAnalyzer extends LogAnalyzer {

    private final EmailService emailService;

    public EmailLogAnalyzer(LogService logService, EmailService emailService) {
        super(logService);
        this.emailService = emailService;
    }

    @Override
    public void analyze(String fileName) {
        if (fileName.length() < 8) {
            try {
                logService.logError("Filename too short: " + fileName);
            } catch (Exception e) {
                emailService.send("admin@xyz.com", "subject", e.getMessage());
            }
        }
    }
}
