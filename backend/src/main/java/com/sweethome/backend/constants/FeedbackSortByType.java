package com.sweethome.backend.constants;

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
