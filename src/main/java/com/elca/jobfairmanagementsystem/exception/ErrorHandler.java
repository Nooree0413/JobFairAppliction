package com.elca.jobfairmanagementsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandler {

    private Error buildError(Exception ex, int status) {

        return new Error(ex.getMessage(), ex.getClass().getCanonicalName(), status);
    }


    ResponseEntity<Error> unHandledException(Exception ex, WebRequest request) {
        int status = 500;
        Error error = buildError(ex, status);
        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(QualificationNotFoundException.class)
    ResponseEntity<Error> qualificationNotFoundException(Exception ex, WebRequest request) {

        int status = 404;
        Error error = buildError(ex, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ExperienceNotFoundException.class)
    ResponseEntity<Error> experienceNotFoundException(Exception ex, WebRequest request) {

        int status = 404;
        Error error = buildError(ex, status);
        return ResponseEntity.status(status).body(error);
    }

//    @ExceptionHandler(NoContentException.class)
//    ResponseEntity<Error> NoContentNotFoundException(Exception ex, WebRequest request) {
//
//        int status = 404;
//        Error error = buildError(ex, status);
//        return ResponseEntity.status(status).body(error);
//    }


}
