package com.example.thang_sample_code.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public class ResponseData<T> {
    private final HttpStatus status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    //PUT, PATCH, DELETE


    public ResponseData(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    //GET, POST


    public ResponseData(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
