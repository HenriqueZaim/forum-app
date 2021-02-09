package com.br.ng.forum.repositories;

import java.util.Optional;
import java.util.UUID;
import com.br.ng.forum.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
    Optional<User> findByDeletedAtNullAndEmail(String email);
    Optional<User> findByDeletedAtNullAndId(UUID id);
}
