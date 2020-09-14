package com.elmar.rest.practice.restking.api.dto.view;

import com.elmar.rest.practice.restking.bussiness.model.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppUserView {

    private long id;
    private String username;
    private String fullname;
    private String status;

    public AppUserView(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getEmail();
        this.fullname = appUser.getName()
                .concat(" ")
                .concat(appUser.getSurname());
        this.status = appUser.getStatus();
    }
}
