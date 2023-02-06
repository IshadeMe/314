package com.ischade.strap.controller;

import com.ischade.strap.dto.request.CreateUserRequestDto;
import com.ischade.strap.model.User;
import com.ischade.strap.service.RoleService;
import com.ischade.strap.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {
    final UserService userService;
    final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getPage(Model model, Authentication auth) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("current", userService.getUserById(((User) auth.getPrincipal()).getId()));
        System.out.println("hihihi");
        return "admin";
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public String createUser(@Valid CreateUserRequestDto dto, BindingResult br) {
        if (br.hasErrors()) {
            return "admin";
        }
        userService.saveUser(dto);
        return "redirect:/admin";
    }
}
