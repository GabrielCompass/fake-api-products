package com.example.fakeapi.infrastructure.exceptions.handler;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        LocalDateTime date,
        int status,
        String path
) {
}
