package com.elmar.rest.practice.restking.bussiness.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AppUserRole {
    private long id;
    private String name;
    private String status;
    private LocalDateTime created;
    private Set<AppUserPermission> permissions = new HashSet<>();
}
