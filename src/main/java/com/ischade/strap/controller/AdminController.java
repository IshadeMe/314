package com.ischade.strap.controller;

import com.ischade.strap.dto.request.CreateUserRequestDto;
import com.ischade.strap.dto.request.UpdateUserRequestDto;
import com.ischade.strap.dto.response.UserDetailsDto;
import com.ischade.strap.model.Role;
import com.ischade.strap.service.RoleService;
import com.ischade.strap.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    final UserService userService;
    final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // <?> - позволит завернуть ошибку.
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<List<UserDetailsDto>> showAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<UserDetailsDto> getUser(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDetailsDto> createUser(@RequestBody CreateUserRequestDto dto) {
        return ResponseEntity.ok(userService.saveUser(dto));
    }

    @PatchMapping(value = "/users/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDetailsDto> updateUser(@RequestBody UpdateUserRequestDto dto, @PathVariable("id") int id) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }
}
