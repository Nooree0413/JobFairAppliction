package com.elca.jobfairmanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Error {
    private String message;
    private String error;
    private int status;
}
