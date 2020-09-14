package com.elmar.rest.practice.restking.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserCreationRequest {
    private String name;
    private String surname;
    private String password;
    private String email;
}
