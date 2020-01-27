package com.elca.jobfairmanagementsystem.tokenresponse;

import lombok.Data;

import java.util.List;

@Data
public class AuthToken {

    private String token;
    private String role;
    private String visa;

    public AuthToken(){

    }

    public AuthToken(String token, String username,String visa){
        this.token = token;
        this.role = username;
        this.visa = visa;
    }
}
