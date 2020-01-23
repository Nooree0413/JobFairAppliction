package com.elca.jobfairmanagementsystem.tokenresponse;

import lombok.Data;

import java.util.List;

@Data
public class AuthToken {

    private String token;
    private String visa;
    private List<String> role;

    public AuthToken(){

    }

    public AuthToken(String token, String username,List<String> role){
        this.token = token;
        this.visa = username;
        this.role = role;
    }
}
