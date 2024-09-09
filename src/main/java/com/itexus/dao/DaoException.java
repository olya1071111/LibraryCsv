package com.itexus.dao;

import java.io.Serial;

public class DaoException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public DaoException() {
        super();
    }

    public DaoException(Exception e) {
        super(e);
    }

}
