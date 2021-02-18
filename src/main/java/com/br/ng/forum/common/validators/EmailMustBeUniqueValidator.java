package com.br.ng.forum.common.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class EmailMustBeUniqueValidator implements ConstraintValidator<EmailMustBeUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Este endereço de e-mail já está em uso").addConstraintViolation();
            return false;
        }

        return true;
    }
    
}
