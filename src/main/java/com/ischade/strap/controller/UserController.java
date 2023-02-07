package com.ischade.strap.controller;


import com.ischade.strap.model.User;
import com.ischade.strap.service.RoleService;
import com.ischade.strap.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;
    final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getInfo(Model model, Authentication authentication) {
        model.addAttribute("current", userService.getUserById(((User) authentication.getPrincipal()).getId()));
        return "user";
    }
}
