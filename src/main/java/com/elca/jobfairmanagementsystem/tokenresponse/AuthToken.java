package com.elca.jobfairmanagementsystem.tokenresponse;

import com.elca.jobfairmanagementsystem.dto.RoleDto;
import lombok.Data;

@Data
public class AuthToken {

    private String token;
    private String visa;
    private RoleDto roleDto;

    public AuthToken() {

    }

    public AuthToken(String token, String visa, RoleDto roleDto) {
        this.token = token;
        this.roleDto = roleDto;
        this.visa = visa;
    }
}
