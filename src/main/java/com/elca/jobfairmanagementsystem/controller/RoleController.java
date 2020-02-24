package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.RoleDto;
import com.elca.jobfairmanagementsystem.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<RoleDto> getRoleByName(@PathVariable String roleName) {
        return new ResponseEntity<>(roleService.getRoleDetailsByRoleName(roleName), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
}
