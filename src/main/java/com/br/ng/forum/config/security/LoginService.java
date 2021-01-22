package com.br.ng.forum.config.security;

import com.br.ng.forum.config.security.user.MyUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    
    public MyUserDetails authenticated(){
        try {
            return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
