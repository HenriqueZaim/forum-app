package com.br.ng.forum.domains.user.persistence;

import com.br.ng.forum.domains.user.domain.User;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
@Service
public class UserPersistenceService {
    
    interface ExcludeFromDelegation {
        User save(User user); 
    }

    @Delegate(excludes = ExcludeFromDelegation.class)
    private final UserRepository userRepository;

    public User save(User user){
        return this.userRepository.save(user);
    }
}
