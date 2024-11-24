package com.itexus.repository;

import java.io.Serial;

public class DaoException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public DaoException() {
    }

    public DaoException(Exception e) {
        super(e);
    }

}
