package com.elmar.rest.practice.restking.bussiness.service;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Username or password was incorrect");
    }
}
