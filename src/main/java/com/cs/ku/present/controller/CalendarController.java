package com.cs.ku.present.controller;

import com.cs.ku.present.exception.InvalidException;
import com.cs.ku.present.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.cs.ku.present.constant.ErrorMessage.INVALID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/holiday")
    public Object getHolidays(
            @RequestParam String countryCode,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false, defaultValue = "public") String type
    ) {
        return switch (type) {
            case "public" -> calendarService.getPublicHolidays(countryCode, from, to);
            case "school" -> calendarService.getSchoolHolidays(countryCode, from, to);
            default -> throw new InvalidException(INVALID);
        };
    }
}
