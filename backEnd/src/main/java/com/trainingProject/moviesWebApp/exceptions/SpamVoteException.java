package com.trainingProject.moviesWebApp.exceptions;

public class SpamVoteException extends RuntimeException {

    public SpamVoteException(String errorMsg) {
        super(errorMsg);
    }
}
