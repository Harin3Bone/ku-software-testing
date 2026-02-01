package com.cs.ku.log.analyzer;

import com.cs.ku.log.service.EmailService;
import com.cs.ku.log.service.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class EmailLogAnalyzerTest {

    private EmailLogAnalyzer emailLogAnalyzer;

    @Mock
    private LogService logService;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.emailLogAnalyzer = new EmailLogAnalyzer(logService, emailService);
    }

    @Test
    void testAnalyzeServiceThrowsSendEmail() {
        // stub
        doThrow(new RuntimeException("fake exception")).when(logService).logError(anyString());

        // when
        emailLogAnalyzer.analyze("abc.ext");

        // verify
        verify(emailService).send("admin@xyz.com", "subject", "fake exception");
    }

}
