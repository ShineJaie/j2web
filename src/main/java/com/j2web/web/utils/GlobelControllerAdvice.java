package com.j2web.web.utils;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 应用于整个项目的控制器<br/>
 * Exception handling methods annotated with @ExceptionHandler.<br/>
 * Model enhancement methods (for adding additional data to the model) annotated with @ModelAttribute.<br/>
 * Binder initialization methods (used for configuring form-handling) annotated with @InitBinder.<br/>
 * Created by wxj on 16-7-25.
 */
@ControllerAdvice
public class GlobelControllerAdvice {

    public static final String DEFAULT_ERROR_VIEW = "/error/500";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView processUnauthenticatedException(HttpServletRequest request, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
