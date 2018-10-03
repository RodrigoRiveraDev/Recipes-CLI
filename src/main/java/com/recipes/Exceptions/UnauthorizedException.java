package com.recipes.Exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException () {
        super("You don't have the permission to execute this action");
    }
}
