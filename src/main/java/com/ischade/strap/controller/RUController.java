package com.ischade.strap.controller;


import com.ischade.strap.model.User;
import com.ischade.strap.service.RoleService;
import com.ischade.strap.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RUController {

    final UserService userService;
    final RoleService roleService;

    public RUController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserById(((User) authentication.getPrincipal()).getId()));
    }
}
