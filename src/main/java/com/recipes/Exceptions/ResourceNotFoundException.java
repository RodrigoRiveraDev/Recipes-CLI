package com.recipes.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class theClass, long id) {
        super("The " + theClass.getSimpleName() + " with id " + id + " was not found");
    }
}
