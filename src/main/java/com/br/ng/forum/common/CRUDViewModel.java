package com.br.ng.forum.common;

import java.util.UUID;

import com.devskiller.friendly_id.FriendlyId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CRUDViewModel {
    
    // ***********************************************************************

    protected String friendlyHash;

	// ***********************************************************************

	public boolean isNew() {
		return null == this.friendlyHash || this.friendlyHash.trim().isEmpty();
	}

	public boolean isUpdate() {
		return !this.isNew();
	}

	public boolean isDeleteButtonVisible() {
		return this.isUpdate();
	}

	public boolean isSaveButtonVisible() {
		return this.isNew();
	}

	public boolean isUpdateButtonVisible() {
		return this.isUpdate();
	}

	public UUID getHash() {
		if(null == friendlyHash || friendlyHash.isEmpty()) {
			return null;
		}
		return FriendlyId.toUuid(this.friendlyHash);
	}

	// ***********************************************************************
    
}
