package com.elmar.rest.practice.restking.bussiness.service;

import static java.lang.String.format;

public class AppUserNotFoundException extends RuntimeException {

    public AppUserNotFoundException(long id) {
        super(format("No such app user found with given id: %d", id));
    }
}
