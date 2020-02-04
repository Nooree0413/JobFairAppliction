package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.UserDto;
import com.elca.jobfairmanagementsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity saveSkill(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
