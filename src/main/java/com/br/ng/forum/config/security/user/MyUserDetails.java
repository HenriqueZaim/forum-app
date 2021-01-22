package com.br.ng.forum.config.security.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.br.ng.forum.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class MyUserDetails implements UserDetails{
    

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRole().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
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
        return active;
    }
}
