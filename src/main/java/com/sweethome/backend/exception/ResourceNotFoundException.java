package com.sweethome.backend.exception;


public class ResourceNotFoundException extends RuntimeException {

    private final String resource;
    private final String field;
    private final Object value;

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(resource + " with " + field + " = " + value + " not found");
        this.resource = resource;
        this.field = field;
        this.value = value;
    }
}