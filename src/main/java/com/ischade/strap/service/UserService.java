package com.ischade.strap.service;

import com.ischade.strap.dto.request.CreateUserRequestDto;
import com.ischade.strap.dto.request.UpdateUserRequestDto;
import com.ischade.strap.dto.response.UserDetailsDto;

import java.util.List;

public interface UserService {

    List<UserDetailsDto> getAllUsers();
    UserDetailsDto getUserById(int id);
    UserDetailsDto saveUser(CreateUserRequestDto dto);
    void deleteUserById(int id);
    UserDetailsDto updateUser(UpdateUserRequestDto dto);
}
