package com.cs.ku.present.dto.response;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record HolidayResponse(
        UUID id,
        String startDate,
        String endDate,
        String type,
        List<Map<String, Object>> name,
        String regionalScope,
        String temporalScope,
        String nationwide
) {
}
