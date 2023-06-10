package com.shaiful.expensetrackerapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ResponseModel <T>{

    @JsonProperty("message")
    String message;

    T data;

    public ResponseModel(T data, int statusCode) {
        this.data = data;
    }

    public ResponseModel(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
