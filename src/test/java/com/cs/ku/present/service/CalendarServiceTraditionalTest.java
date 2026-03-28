package com.cs.ku.present.service;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalendarServiceTraditionalTest {

    @InjectMocks
    private CalendarService calendarService;

    @Mock
    private RestClient openHolidayClient;

    @Test
    void testGetPublicHolidays() {
        // Given
        when(openHolidayClient.get().uri(anyString())).thenReturn(null);
    }

}
