package com.ischade.strap.mapper;

import com.ischade.strap.config.WebSecurityConfig;
import com.ischade.strap.dto.request.CreateUserRequestDto;
import com.ischade.strap.dto.request.UpdateUserRequestDto;
import com.ischade.strap.dto.response.UserDetailsDto;
import com.ischade.strap.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = WebSecurityConfig.class)
public interface UserMapper {

    UserDetailsDto userToUserDetails(User user);

    @Mapping(target = "password", expression = "java(WebSecurityConfig.passwordEncoder().encode(dto.password()))")
    User createDtoToUser(CreateUserRequestDto dto);

    @Mapping(target = "password", expression = "java(WebSecurityConfig.passwordEncoder().encode(dto.password()))")
    User updateDtoToUser(UpdateUserRequestDto dto);


}
