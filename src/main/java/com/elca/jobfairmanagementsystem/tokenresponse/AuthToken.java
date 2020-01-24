package com.elca.jobfairmanagementsystem.tokenresponse;

import lombok.Data;

import java.util.List;

@Data
public class AuthToken {

    private String token;
    private String visa;

    public AuthToken(){

    }

    public AuthToken(String token, String username){
        this.token = token;
        this.visa = username;
    }
}
