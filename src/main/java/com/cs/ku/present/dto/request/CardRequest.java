package com.cs.ku.present.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CardRequest(
        @NotBlank
        String title,

        String description,
        String status
) {
}
