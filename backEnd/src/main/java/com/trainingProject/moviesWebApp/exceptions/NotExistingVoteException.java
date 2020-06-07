package com.trainingProject.moviesWebApp.exceptions;

public class NotExistingVoteException extends RuntimeException {

    public NotExistingVoteException(String errorMsg) {
        super(errorMsg);
    }
}
