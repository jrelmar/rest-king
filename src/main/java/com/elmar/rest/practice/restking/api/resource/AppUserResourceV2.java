package com.elmar.rest.practice.restking.api.resource;

import com.elmar.rest.practice.restking.api.dto.request.AppUserCreationRequest;
import com.elmar.rest.practice.restking.api.dto.view.AppUserView;
import com.elmar.rest.practice.restking.bussiness.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/users", headers = "x-api-version=v2")
@RequiredArgsConstructor
public class AppUserResourceV2 {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AppUserView> createUser(UriComponentsBuilder uriComponentsBuilder,
                                                  @RequestBody AppUserCreationRequest creationRequest) {
        AppUserView newAppUser = appUserService.createNewAppUser(creationRequest);
        UriComponents uriComponents = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAppUser.getId());
        return ResponseEntity.created(uriComponents.toUri())
                .body(newAppUser);
    }
}
