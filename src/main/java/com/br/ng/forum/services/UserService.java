package com.br.ng.forum.services;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.br.ng.forum.config.exceptions.ObjectNotFoundException;
import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.models.User;
import com.br.ng.forum.repositories.UserRepository;
import com.br.ng.forum.services.storage.ImageStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ImageStorage imageStorage;

    public User save(User user){
        user.setCreatedAt(OffsetDateTime.now());
        user.setPassword(bCrypt.encode(user.getPassword().trim()));
        return userRepository.save(user);
    }

    public void addImage(Long id, MultipartFile image){
        User user = findById(id);
        user.setImage(imageStorage.save(image));
        userRepository.save(user);
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User find(){
        return userRepository.findById(loginService.authenticated().getId()).get();
    }
    
}
