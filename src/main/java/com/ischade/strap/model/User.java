package com.ischade.strap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 5, max = 10)
    String login;

    @NotBlank
    @Size(min = 3, max = 40)
    String firstName;

    @NotBlank
    @Size(min = 3, max = 40)
    String lastName;

    @Min(1)
    @Max(120)
    byte age;

    @Column(unique = true)
    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 6)
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty
    List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
