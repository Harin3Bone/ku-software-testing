package com.cs.ku.present.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

    public static final String INVALID = "Invalid payload";
    public static final String NOT_FOUND = "%s not found with id: %s";

}
