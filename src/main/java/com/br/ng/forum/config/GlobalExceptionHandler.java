package com.br.ng.forum.config;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.br.ng.forum.config.exceptions.ObjectNotFoundException;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String ERROR_FOLDER = "errors";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", req.getRequestURL());
        mv.setViewName(ERROR_FOLDER + "/default");
        return mv;
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ModelAndView notFoundExceptionHandler(HttpServletRequest req, ObjectNotFoundException e) throws Exception {

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.addObject("url", req.getRequestURL());
        mv.setViewName(ERROR_FOLDER+"/404");
        return mv;
    }

    @ExceptionHandler(value = AmazonServiceException.class)
    public ModelAndView amazonServiceHandler(HttpServletRequest req, AmazonServiceException e) throws Exception {

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.addObject("url", req.getRequestURL());
        mv.setViewName(ERROR_FOLDER+"/404");
        return mv;
    }

    @ExceptionHandler(value = AmazonS3Exception.class)
    public ModelAndView fileExceptionHandler(HttpServletRequest req, AmazonS3Exception e) throws Exception {

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.addObject("url", req.getRequestURL());
        mv.setViewName(ERROR_FOLDER+"/404");
        return mv;
    }

}
