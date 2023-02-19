package com.ischade.strap.config;

import com.ischade.strap.dao.RoleRepository;
import com.ischade.strap.dao.UserRepository;
import com.ischade.strap.model.Role;
import com.ischade.strap.model.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataLoader implements ApplicationRunner {

    final RoleRepository roleRepository;
    final UserRepository userRepository;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(Role.builder().name("ADMIN").build());
            roleRepository.save(Role.builder().name("USER").build());
        }
        Set<Role> roles = roleRepository.getAll();
        if (userRepository.findAll().isEmpty() || userRepository.findUserByLogin("admin") == null) {
            userRepository.save(User.builder()
                    .login("admin")
                    .password(WebSecurityConfig.passwordEncoder().encode("admin"))
                    .firstName("admin")
                    .lastName("admin")
                    .age((byte) 30)
                    .email("admin@mail.ru")
                    .roles(roles)
                    .build()
            );
        }
        if (userRepository.findUserByLogin("userr") == null) {
            roles.removeIf((role -> role.getName().equals("ADMIN")));
            userRepository.save(User.builder()
                    .login("userr")
                    .password(WebSecurityConfig.passwordEncoder().encode("userr"))
                    .firstName("userr")
                    .lastName("userr")
                    .age((byte) 25)
                    .email("user@mail.ru")
                    .roles(roles)
                    .build()
            );
        }
    }
}
