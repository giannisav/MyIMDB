package com.trainingProject.moviesWebApp.enums;

public enum SortingOrder {
    ASC("ASC"),
    DESC("DESC");

    private final String order;

    SortingOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
