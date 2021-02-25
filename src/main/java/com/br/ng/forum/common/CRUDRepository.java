package com.br.ng.forum.common;

import java.util.Optional;
import java.util.UUID;

import com.br.ng.forum.domains.DomainEntity;

public interface CRUDRepository<E extends DomainEntity> {
    
    <T> Optional<T> findByHash(UUID hash, Class<T> type);
    <T> Optional<T> findByDeletedAtNullAndHash(UUID hash, Class<T> type);
}
