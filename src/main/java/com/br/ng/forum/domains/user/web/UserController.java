package com.br.ng.forum.domains.user.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.br.ng.forum.common.CRUDController;
import com.br.ng.forum.common.CRUDControllerInitializer;
import com.br.ng.forum.config.exceptions.AuthorizationException;
import com.br.ng.forum.config.security.LoginService;
import com.br.ng.forum.domains.question.topic.web.TopicService;
import com.br.ng.forum.domains.question.topic.web.viewmodel.TopicVM;
import com.br.ng.forum.domains.user.domain.User;
import com.br.ng.forum.domains.user.web.viewmodel.UserVM;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(UserController.BASE_URL)
public class UserController extends CRUDController<UserVM, User> {

    public static final String BASE_PATH = "user";
    public static final String BASE_URL = "/user";

    private final UserService userService;
    private final LoginService loginService;
    private final TopicService topicService;

    public UserController(UserService userService, LoginService loginService, TopicService topicService) {
        super(CRUDControllerInitializer
        .<UserVM, User>builder()
            .basePath(BASE_PATH)
            .baseURL(BASE_URL)
            .CRUDApplicationService(userService)
        .build());
        this.userService = userService;
        this.loginService = loginService;
        this.topicService = topicService;
    }

    @Override
    public ModelAndView search(RedirectAttributes redirectAttributes, HttpSession session) {
        UUID userHash = loginService.authenticated().getHash();

        Optional<UserVM> userVM = userService.getEnabledForEditing(userHash);
        List<TopicVM> userTopicsVM = topicService.getAllAsList(userHash);

        ModelAndView mv = getSearchModelAndView();
        mv.addObject("userVM", userVM.get());
        mv.addObject("userTopicsVM", userTopicsVM);

        return mv;
    }

    @Override
    public ModelAndView edit(@PathVariable String friendlyHash, RedirectAttributes redirectAttributes, HttpSession session) {
        if(friendlyHash.equals(loginService.authenticated().getFriendlyHash()))
            return search(redirectAttributes, session);
        else{
            throw new AuthorizationException("Não autorizado");
        }
    }




}
