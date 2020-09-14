package com.elmar.rest.practice.restking.bussiness.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppUser {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String status;
    private LocalDateTime created;
}
