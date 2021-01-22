package com.br.ng.forum.services;

import java.time.OffsetDateTime;

import com.br.ng.forum.models.User;
import com.br.ng.forum.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    // salvar
    public User save(User user){
        user.setCreatedAt(OffsetDateTime.now());
        user.setPassword(bCrypt.encode(user.getPassword().trim()));
        return userRepository.save(user);
    }
    // alterar

    // listar

}
