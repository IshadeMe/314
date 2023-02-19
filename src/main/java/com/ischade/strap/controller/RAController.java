package com.ischade.strap.controller;

import com.ischade.strap.dto.request.CreateUserRequestDto;
import com.ischade.strap.dto.request.UpdateUserRequestDto;
import com.ischade.strap.model.Role;
import com.ischade.strap.service.RoleService;
import com.ischade.strap.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RAController {

    final UserService userService;
    final RoleService roleService;

    public RAController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> showAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto dto) {
        return ResponseEntity.ok(userService.saveUser(dto));
    }

    @PatchMapping(value = "/users/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequestDto dto, @PathVariable("id") int id) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }
}
