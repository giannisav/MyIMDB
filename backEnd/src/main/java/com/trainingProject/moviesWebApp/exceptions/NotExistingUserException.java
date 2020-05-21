package com.trainingProject.moviesWebApp.exceptions;

public class NotExistingUserException extends RuntimeException {

    public NotExistingUserException(String errorMsg) {
        super(errorMsg);

    }

}

