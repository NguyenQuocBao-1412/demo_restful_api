package com.myproject.uniclub.exception;

public class InsertException extends RuntimeException {
    private ErrorStatusCode errorStatusCode;

    public InsertException(ErrorStatusCode errorStatusCode) {
        super(errorStatusCode.getMessage());
        this.errorStatusCode = errorStatusCode;
    }

    public ErrorStatusCode getErrorStatusCode() {
        return errorStatusCode;
    }

    public void setErrorStatusCode(ErrorStatusCode errorStatusCode) {
        this.errorStatusCode = errorStatusCode;
    }
}
