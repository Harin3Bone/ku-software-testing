package com.cs.ku.present.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class APIConfig {

    @Bean
    public RestClient openHolidayClient() {
        return RestClient.builder()
                .baseUrl("openholidaysapi.org")
                .build();
    }

}
