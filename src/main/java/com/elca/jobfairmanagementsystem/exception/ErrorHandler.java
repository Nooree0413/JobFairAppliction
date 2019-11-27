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

    @ExceptionHandler(CandidateScreeningNotFoundException.class)
    ResponseEntity<Error> candidateScreeningNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    ResponseEntity<Error> candidateNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(CandidateSkillNotFoundException.class)
    ResponseEntity<Error> candidateSkillNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(CandidateVenueJobNotFoundException.class)
    ResponseEntity<Error> candidateVenueJobNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(JobNotFoundException.class)
    ResponseEntity<Error> JobNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(SkillNotFoundException.class)
    ResponseEntity<Error> SkillNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(VenueNotFoundException.class)
    ResponseEntity<Error> VenueNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(VenueJobNotFoundException.class)
    ResponseEntity<Error> VenueJobNotFoundException(Exception ex, WebRequest request){
        int status = 404;
        Error error = buildError(ex,status);
        return ResponseEntity.status(status).body(error);
    }
}
