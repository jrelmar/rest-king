package com.elmar.rest.practice.restking.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatusChangeRequest {
    private String targetStatus;
}
