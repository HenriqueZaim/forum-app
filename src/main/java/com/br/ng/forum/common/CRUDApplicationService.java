package com.br.ng.forum.common;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.br.ng.forum.domains.DomainEntity;

public interface CRUDApplicationService<VM extends CRUDViewModel, E extends DomainEntity>{
    
    E save(VM vm);

    Optional<VM> getEnabledForEditing(UUID hash);

	void removeLogicallyByHash(UUID hash);

    List<VM> getAllAsList(UUID hash); ///
}
