package com.br.ng.forum.config.security.user;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.br.ng.forum.domains.user.domain.User;
import com.devskiller.friendly_id.FriendlyId;

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

    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean active;
    private UUID hash;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.id = user.getId();
        this.hash = user.getHash();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.getDeletedAt() == null;
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

    public String getFriendlyHash(){
        return FriendlyId.toFriendlyId(hash);
    }
}
