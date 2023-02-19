package com.ischade.strap.service;

import com.ischade.strap.dao.RoleRepository;
import com.ischade.strap.model.Role;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getRoles() {
        return roleRepository.getAll();
    }
}
