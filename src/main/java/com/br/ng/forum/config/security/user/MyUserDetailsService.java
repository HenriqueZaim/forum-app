package com.br.ng.forum.config.security.user;

import java.util.Optional;

import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
    
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByDeletedAtNullAndEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Não encontrado: " + email));

        return user.map(MyUserDetails::new).get();
    }
}
