package com.sweethome.backend.enums;

public enum PropertySortByType {
    PRICE("price"),
    CREATED_AT("createdAt"),
    ROOMS("rooms"),
    USABLE_AREA("usableArea");

    private final String field;

    PropertySortByType(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
