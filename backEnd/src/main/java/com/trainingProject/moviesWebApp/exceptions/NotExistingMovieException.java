package com.trainingProject.moviesWebApp.exceptions;

public class NotExistingMovieException extends RuntimeException {

    public NotExistingMovieException(String errorMsg) {
        super(errorMsg);
    }
}
