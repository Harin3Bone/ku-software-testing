package com.cs.ku.lecture.log.analyzer;

import com.cs.ku.lecture.log.service.LogService;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("java:S2094")
@RequiredArgsConstructor
public abstract class LogAnalyzer {

    protected final LogService logService;

    public abstract void analyze(String fileName);

}
