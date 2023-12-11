package com.emma.rest.api;

import java.io.Serializable;

public class RestResponseWrapper implements Serializable {
    public boolean success;
    private String message;

    public RestResponseWrapper() {
        this.success = true;
        this.message = "OK";
    }

    public RestResponseWrapper(String message) {
        this.message = message;
        this.success = true;
    }

    public RestResponseWrapper(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
