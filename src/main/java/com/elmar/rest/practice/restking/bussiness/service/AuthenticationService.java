package com.elmar.rest.practice.restking.bussiness.service;

import com.elmar.rest.practice.restking.api.dto.request.AuthenticationRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public void authenticateUser(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest.getUsername().equals("system") &&
                authenticationRequest.getPassword().equals("system")) {
        } else {
            throw new AuthenticationException();
        }
    }

}
