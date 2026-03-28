package com.cs.ku.present.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final RestClient openHolidayClient;

    public Object getPublicHolidays(String countryCode, String from, String to) {
        return openHolidayClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/PublicHolidays")
                        .queryParam("countryIsoCode", countryCode)
                        .queryParam("validFrom", from)
                        .queryParam("validTo", to)
                        .build())
                .retrieve()
                .body(Object.class);
    }

    public Object getSchoolHolidays(String countryCode, String from, String to) {
        return openHolidayClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/SchoolHolidays")
                        .queryParam("countryIsoCode", countryCode)
                        .queryParam("validFrom", from)
                        .queryParam("validTo", to)
                        .build())
                .retrieve()
                .body(Object.class);
    }

}
