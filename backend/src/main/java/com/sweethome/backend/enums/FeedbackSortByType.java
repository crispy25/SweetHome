package com.sweethome.backend.enums;

public enum FeedbackSortByType {
    CREATED_AT("createdAt"),
    RATING("rating");

    private final String field;

    FeedbackSortByType(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
