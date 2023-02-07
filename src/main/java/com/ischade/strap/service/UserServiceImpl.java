package com.ischade.strap.service;

import com.ischade.strap.dao.RoleRepository;
import com.ischade.strap.dao.UserRepository;
import com.ischade.strap.dto.request.CreateUserRequestDto;
import com.ischade.strap.dto.request.UpdateUserRequestDto;
import com.ischade.strap.dto.response.UserDetailsDto;
import com.ischade.strap.mapper.UserMapper;
import com.ischade.strap.model.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDetailsDto> getAllUsers() {
        return userRepository.findAll().stream().map((userMapper::userToUserDetails)).collect(Collectors.toList());
    }

    @Override
    public UserDetailsDto getUserById(int id) {
        return userMapper.userToUserDetails(userRepository.getReferenceById(id));
    }

    @Transactional
    @Override
    public UserDetailsDto saveUser(CreateUserRequestDto dto) {
        return userMapper.userToUserDetails(userRepository.save(userMapper.createDtoToUser(dto)));
    }

    @Transactional
    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDetailsDto updateUser(UpdateUserRequestDto dto) {
        return userMapper.userToUserDetails(userRepository.save(userMapper.updateDtoToUser(dto)));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
