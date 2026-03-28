package com.cs.ku.present.constant;

import java.util.HashMap;
import java.util.Map;

public enum Status {

    DRAFT,
    ACTIVE,
    INACTIVE;

    private static final Map<String,Status> STATUS_MAP = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            STATUS_MAP.put(status.name().toLowerCase(), status);
        }
    }

    public static Status fromString(String status) {
        return STATUS_MAP.get(status.toLowerCase());
    }

}
