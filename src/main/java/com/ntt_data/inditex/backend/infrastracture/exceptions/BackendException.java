package com.ntt_data.inditex.backend.infrastracture.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@Slf4j
public class BackendException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public static void handleGenericException(Exception e, String methodName) {
        String errorMessage = extractErrorMessage(e);
        log.error("{}:{}", methodName, errorMessage);
        throw new BackendException(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static String extractErrorMessage(Exception e) {
        Throwable cause = e.getCause() == null ? e.getCause().getCause() : e;
        return cause.getMessage();
    }
}
