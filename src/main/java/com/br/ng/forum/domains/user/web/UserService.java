package com.br.ng.forum.domains.user.web;

import com.br.ng.forum.common.CRUDApplicationService;
import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.web.viewmodel.UserVM;

public interface UserService extends CRUDApplicationService<UserVM, User> {
    
}
