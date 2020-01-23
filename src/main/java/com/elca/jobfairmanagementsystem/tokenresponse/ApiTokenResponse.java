package com.elca.jobfairmanagementsystem.tokenresponse;

import lombok.Data;

@Data
public class ApiTokenResponse<T> {
    private int status;
    private String message;
    private T result;

    public ApiTokenResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
