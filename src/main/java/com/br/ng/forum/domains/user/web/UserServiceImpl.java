package com.br.ng.forum.domains.user.web;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.br.ng.forum.config.exceptions.ObjectNotFoundException;
import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.persistence.UserPersistenceService;
import com.br.ng.forum.domains.user.web.viewmodel.UserVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPersistenceService userPersistenceService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    // public void addImage(Long id, MultipartFile image) {
    // User user = findById(id);
    // user.setImage(imageStorage.save(image));
    // userRepository.save(user);
    // }

    @Override
    @Transactional
    public User save(UserVM vm) {
        User user = new User();

        if (vm.isUpdate()) {
            Optional<User> optionalUser = this.userPersistenceService.findByDeletedAtNullAndHash(vm.getHash());
            if (optionalUser.isPresent())
                user = optionalUser.get();
            else 
                throw new ObjectNotFoundException("Usuário não encontrado");
        }

        vm.fill(user, bCrypt);
        this.userPersistenceService.save(user);

        return user;
    }

    @Override
    public Optional<UserVM> getEnabledForEditing(UUID hash) {
        Optional<User> user = this.userPersistenceService.findByDeletedAtNullAndHash(hash, User.class);
        if(!user.isPresent()){
            throw new ObjectNotFoundException("Usuário não encontrado");
        }

        return Optional.of(UserVM.from(user.get()));
    }

    @Override
    public void removeLogicallyByHash(UUID hash) {
        Optional<User> optionalUser = this.userPersistenceService.findByDeletedAtNullAndHash(hash, User.class);

        if(!optionalUser.isPresent()){
            throw new ObjectNotFoundException("Usuário não encontrado");
        }

        User user = optionalUser.get();
        user.setDeletedAt(OffsetDateTime.now());
        this.userPersistenceService.save(user);
    }

    @Override
    public List<UserVM> getAllAsList(UUID hash) {
        // TODO Auto-generated method stub
        return null;
    }

    // public User find(){
    //     return userRepository.findById(loginService.authenticated().getId()).get();
    // }
    
}
