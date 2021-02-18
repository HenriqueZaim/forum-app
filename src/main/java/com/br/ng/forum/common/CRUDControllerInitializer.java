package com.br.ng.forum.common;

import com.br.ng.forum.domains.DomainEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CRUDControllerInitializer<VM extends CRUDViewModel, E extends DomainEntity> {
    
    @NonNull
	String basePath;
	@NonNull
	String baseURL;
    @NonNull
	CRUDApplicationService<VM, E> CRUDApplicationService;
}
