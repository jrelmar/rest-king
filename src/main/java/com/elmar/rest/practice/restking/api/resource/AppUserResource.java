package com.elmar.rest.practice.restking.api.resource;

import com.elmar.rest.practice.restking.api.dto.request.AppUserCreationRequest;
import com.elmar.rest.practice.restking.api.dto.request.AppUserUpdateRequest;
import com.elmar.rest.practice.restking.api.dto.request.RoleCreationRequest;
import com.elmar.rest.practice.restking.api.dto.request.UserStatusChangeRequest;
import com.elmar.rest.practice.restking.api.dto.view.AppUserRoleView;
import com.elmar.rest.practice.restking.api.dto.view.AppUserView;
import com.elmar.rest.practice.restking.bussiness.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", headers = "x-api-version=v1")
@RequiredArgsConstructor
public class AppUserResource {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AppUserView> createUser(@RequestBody AppUserCreationRequest creationRequest) {
        AppUserView newAppUser = appUserService.createNewAppUser(creationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppUser);
    }

    @GetMapping
    public ResponseEntity<List<AppUserView>> getUsers() {
        return ResponseEntity.ok(
                appUserService.getAllUsers()
        );
    }

    @GetMapping("/{uid}")
    public ResponseEntity<AppUserView> getUser(@PathVariable("uid") Long userId) {
        AppUserView appUserById = appUserService.getAppUserById(userId);
        return ResponseEntity.ok(appUserById);
    }


    @PutMapping("/{uid}")
    public ResponseEntity<AppUserView> updateUser(@PathVariable("uid") Long userId,
                                                  @RequestBody AppUserUpdateRequest userUpdateRequest) {
        AppUserView appUserView = appUserService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.ok(appUserView);
    }

    @PatchMapping("/{uid}")
    public ResponseEntity<AppUserView> changeUserStatus(@PathVariable("uid") Long userId,
                                                        @RequestBody UserStatusChangeRequest statusChangeRequest) {
        AppUserView appUserView = appUserService.changeUserStatus(userId, statusChangeRequest);
        return ResponseEntity.ok(appUserView);
    }

    @GetMapping("/{uid}/roles")
    public ResponseEntity<List<AppUserRoleView>> getUserRoles(@PathVariable("uid") Long userId) {
        return ResponseEntity.ok(
                appUserService.getRolesOfUser(userId)
        );
    }

    @PostMapping("/{uid}/roles")
    public ResponseEntity<List<AppUserRoleView>> addRole(@PathVariable("uid") Long userId,
                                                         @RequestBody RoleCreationRequest creationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                appUserService.addRoleToUser(userId, creationRequest)
        );
    }

}
