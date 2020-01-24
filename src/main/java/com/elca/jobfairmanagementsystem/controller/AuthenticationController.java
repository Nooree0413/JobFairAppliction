package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.config.JwtTokenUtil;
import com.elca.jobfairmanagementsystem.dto.LoginDto;
import com.elca.jobfairmanagementsystem.dto.UserDto;
import com.elca.jobfairmanagementsystem.service.UserService;
import com.elca.jobfairmanagementsystem.tokenresponse.ApiTokenResponse;
import com.elca.jobfairmanagementsystem.tokenresponse.AuthToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/token")
public class AuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationController(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiTokenResponse<AuthToken> getToken(@RequestBody LoginDto loginDto) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getVisa(),loginDto.getPassword()));
        SecurityContextHolder.getContext().getAuthentication();
        final UserDto user = userService.loadUserByVisa(loginDto.getVisa());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiTokenResponse<>(200,"Success",new AuthToken(token, user.getVisa()));
    }
}
