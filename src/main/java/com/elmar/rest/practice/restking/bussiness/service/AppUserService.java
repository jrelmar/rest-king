package com.elmar.rest.practice.restking.bussiness.service;

import com.elmar.rest.practice.restking.api.dto.request.AppUserCreationRequest;
import com.elmar.rest.practice.restking.api.dto.request.AppUserUpdateRequest;
import com.elmar.rest.practice.restking.api.dto.request.RoleCreationRequest;
import com.elmar.rest.practice.restking.api.dto.request.UserStatusChangeRequest;
import com.elmar.rest.practice.restking.api.dto.view.AppUserRoleView;
import com.elmar.rest.practice.restking.api.dto.view.AppUserView;
import com.elmar.rest.practice.restking.bussiness.model.AppUser;
import com.elmar.rest.practice.restking.bussiness.model.AppUserRole;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private static final Map<Long, AppUser> APP_USERS = new ConcurrentHashMap<>();
    private static final Map<Long, List<AppUserRole>> APP_USER_ROLES = new ConcurrentHashMap<>();

    private static final AtomicLong ID_POOL = new AtomicLong(0L);

    public List<AppUserView> getAllUsers() {
        return APP_USERS.values()
                .stream()
                .map(AppUserView::new)
                .collect(Collectors.toList());
    }

    public AppUserView createNewAppUser(AppUserCreationRequest creationRequest) {

        long generatedId = ID_POOL.incrementAndGet();

        AppUser appUser = new AppUser();
        appUser.setId(generatedId);
        appUser.setCreated(LocalDateTime.now());
        appUser.setEmail(creationRequest.getEmail());
        appUser.setName(creationRequest.getName());
        appUser.setSurname(creationRequest.getSurname());
        appUser.setPassword(creationRequest.getPassword());
        appUser.setStatus("ACTIVE");

        APP_USERS.put(generatedId, appUser);

        return new AppUserView(appUser);
    }

    public AppUserView getAppUserById(long uid) {
        AppUser appUser = APP_USERS.get(uid);
        if (appUser == null) {
            throw new AppUserNotFoundException(uid);
        }
        return new AppUserView(appUser);
    }


    public AppUserView updateUser(long uid, AppUserUpdateRequest updateRequest) {
        APP_USERS.computeIfPresent(uid, (aLong, updatedAppUser) -> {
            updatedAppUser.setName(updateRequest.getName());
            updatedAppUser.setSurname(updateRequest.getSurname());
            updatedAppUser.setPassword(updateRequest.getPassword());
            return updatedAppUser;
        });

        AppUser appUser = APP_USERS.get(uid);

        if (appUser == null) {
            throw new AppUserNotFoundException(uid);
        }

        return new AppUserView(appUser);
    }


    public AppUserView changeUserStatus(long uid, UserStatusChangeRequest statusChangeRequest) {

        APP_USERS.computeIfPresent(uid, (aLong, updatedAppUser) -> {
            updatedAppUser.setStatus(statusChangeRequest.getTargetStatus());
            return updatedAppUser;
        });

        AppUser appUser = APP_USERS.get(uid);

        if (appUser == null) {
            throw new AppUserNotFoundException(uid);
        }

        return new AppUserView(appUser);
    }


    public List<AppUserRoleView> getRolesOfUser(long uid) {
        return APP_USER_ROLES.getOrDefault(uid, Collections.emptyList())
                .stream()
                .map(AppUserRoleView::new)
                .collect(Collectors.toList());
    }

    public List<AppUserRoleView> addRoleToUser(long uid, RoleCreationRequest roleCreationRequest) {
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setCreated(LocalDateTime.now());
        appUserRole.setId(ID_POOL.incrementAndGet());
        appUserRole.setName(roleCreationRequest.getName());
        appUserRole.setPermissions(roleCreationRequest.getPermissions());
        appUserRole.setStatus("ENABLED");

        List<AppUserRole> roles = new ArrayList<>();
        roles.add(appUserRole);

        APP_USER_ROLES.merge(uid, roles, (prev, value) -> {
            prev.add(appUserRole);
            return prev;
        });

        return getRolesOfUser(uid);
    }

}
