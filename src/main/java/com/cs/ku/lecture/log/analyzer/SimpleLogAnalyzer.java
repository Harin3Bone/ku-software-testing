package com.cs.ku.lecture.log.analyzer;

import com.cs.ku.lecture.log.service.LogService;

public class SimpleLogAnalyzer extends LogAnalyzer {

    public SimpleLogAnalyzer(LogService logService) {
        super(logService);
    }

    @Override
    public void analyze(String fileName) {
        if (fileName.length() < 8) {
            logService.logError("Filename too short: " + fileName);
        }
    }
}
