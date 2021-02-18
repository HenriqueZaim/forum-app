package com.br.ng.forum.domains.user.web.viewmodel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.br.ng.forum.common.CRUDViewModel;
import com.br.ng.forum.common.validators.EmailMustBeUnique;
import com.br.ng.forum.domains.user.domain.User;
import com.devskiller.friendly_id.FriendlyId;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVM extends CRUDViewModel {

    @NotBlank(message = "Campo Nome não pode ser vazio")
    private String name;

    @EmailMustBeUnique
    @NotBlank(message = "Campo E-mail não pode ser vazio")
    @Email(message = "Insira um E-mail válido")
    private String email;

    @NotBlank(message = "Campo Senha não pode ser vazio")
    private String password;

    public void fill(User user, PasswordEncoder passwordEncoder){
        user.setName(name);
        user.setEmail(email);

        if(null == friendlyHash)
            user.setPassword(passwordEncoder.encode(password));

    }

    // mando pro front
    public static UserVM from(User user){
        UserVM userVM = new UserVM();
        userVM.setName(user.getName()); 
        userVM.setEmail(user.getEmail());
        userVM.setFriendlyHash(FriendlyId.toFriendlyId(user.getHash()));

        return userVM;
    }


}
