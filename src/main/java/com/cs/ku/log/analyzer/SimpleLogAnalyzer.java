package com.cs.ku.log.analyzer;

import com.cs.ku.log.service.LogService;

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
