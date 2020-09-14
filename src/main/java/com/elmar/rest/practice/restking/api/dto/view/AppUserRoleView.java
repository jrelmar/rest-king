package com.elmar.rest.practice.restking.api.dto.view;

import com.elmar.rest.practice.restking.bussiness.model.AppUserPermission;
import com.elmar.rest.practice.restking.bussiness.model.AppUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AppUserRoleView {
    private long id;
    private String name;
    private String status;
    private Set<AppUserPermission> permissions = new HashSet<>();

    public AppUserRoleView(AppUserRole appUserRole) {
        this.id = appUserRole.getId();
        this.name = appUserRole.getName();
        this.status = appUserRole.getStatus();
        this.permissions = appUserRole.getPermissions();
    }
}
