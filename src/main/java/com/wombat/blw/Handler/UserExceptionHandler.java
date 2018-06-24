package com.wombat.blw.Handler;

import com.wombat.blw.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleUserAuthorizeException(UserAuthorizeException e) {
        return new ModelAndView("common/error");
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleBaseException(BaseException e) {
        return new ModelAndView("common/error");
    }

    @ExceptionHandler(FileIOException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleFileIOException(FileIOException e) {
        return new ModelAndView("common/error");
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleInvalidParameterException(InvalidParameterException e) {
        return new ModelAndView("common/error");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(NotFoundException e) {
        return new ModelAndView("common/error");
    }

    @ExceptionHandler(ProjectStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleProjectStatusException(ProjectStatusException e) {
        return new ModelAndView("common/error");
    }

}
