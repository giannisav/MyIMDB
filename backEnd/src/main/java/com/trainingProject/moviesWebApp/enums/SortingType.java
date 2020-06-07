package com.trainingProject.moviesWebApp.enums;

public enum SortingType {
    LIKES("likes"),
    DISLIKES("dislikes");

    private final String field;

    SortingType(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
