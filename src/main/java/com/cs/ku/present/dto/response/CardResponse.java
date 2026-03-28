package com.cs.ku.present.dto.response;

import java.util.UUID;

public record CardResponse(
        UUID id,
        String title,
        String description,
        String status,
        String createdTimestamp,
        String createdBy,
        String updatedTimestamp,
        String updatedBy
) {
}
