package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.UserRoleDto;
import com.elca.jobfairmanagementsystem.service.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user-role")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public ResponseEntity saveUserRole(@RequestBody UserRoleDto userRoleDto){
        userRoleService.saveUserRole(userRoleDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        return new ResponseEntity<>(userRoleService.getAllUserRole(), HttpStatus.OK);
    }
}
