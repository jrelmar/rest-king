package com.elmar.rest.practice.restking.api.dto.request;

import com.elmar.rest.practice.restking.bussiness.model.AppUserPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoleCreationRequest {
    private String name;
    private Set<AppUserPermission> permissions = new HashSet<>();
}
