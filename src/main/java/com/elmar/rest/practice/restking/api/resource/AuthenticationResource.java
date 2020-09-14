package com.elmar.rest.practice.restking.api.resource;

import com.elmar.rest.practice.restking.api.dto.request.AuthenticationRequest;
import com.elmar.rest.practice.restking.bussiness.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    @PostMapping(headers = {"X-api-version=v1"})
    public ResponseEntity<Void> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationService.authenticateUser(authenticationRequest);
        return ResponseEntity.ok().build();
    }

}
