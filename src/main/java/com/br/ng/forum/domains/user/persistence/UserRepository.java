package com.br.ng.forum.domains.user.persistence;

import java.util.Optional;
import java.util.UUID;

import com.br.ng.forum.common.CRUDRepository;
import com.br.ng.forum.domains.user.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CRUDRepository<User>, JpaRepository<User, Long>{
    
    Optional<User> findByDeletedAtNullAndEmail(String email);
    Optional<User> findByDeletedAtNullAndHash(UUID hash);
    Optional<User> findByEmail(String email);
}
