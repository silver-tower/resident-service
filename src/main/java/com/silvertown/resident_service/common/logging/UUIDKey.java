package com.silvertown.resident_service.common.logging;

import lombok.Getter;

@Getter
public enum UUIDKey {
    REQUEST("requestId");
    final String key;

    UUIDKey(String requestId) {
        this.key = requestId;
    }
}
