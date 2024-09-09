package com.itexus.service;

import java.io.Serial;

public class LogicException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public LogicException(String message) {
        super(message);
    }

    public LogicException(Exception e) {
        super(e);
    }

}