package com.cs.ku.log.analyzer;

import com.cs.ku.log.service.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class SimpleLogAnalyzerTest {

    private SimpleLogAnalyzer simpleLogAnalyzer;

    @Mock
    private LogService logService;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.simpleLogAnalyzer = new SimpleLogAnalyzer(logService);
    }

    @Test
    void testAnalyzeTooShortFileNameCallsService() {
        // when
        simpleLogAnalyzer.analyze("abc.ext");

        // verify
        verify(logService).logError("Filename too short: abc.ext");
    }

}
