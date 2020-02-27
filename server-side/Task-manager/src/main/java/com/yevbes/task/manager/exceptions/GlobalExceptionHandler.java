package com.yevbes.task.manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Change status code in response to 404
     *
     * @param response Response
     * @throws IOException exception
     */
    @ExceptionHandler({TaskNotFoundException.class, TaskDeleteException.class, TaskUpdateException.class})
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    /**
     * Change status code in response to 200
     *
     * @param response Response
     * @throws IOException exception
     */
    @ExceptionHandler(TaskListIsEmptyException.class)
    public void springHandleEmptyList(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.OK.value());
    }



    /**
     * JSR 303 Validation error
     * For Validating Path Variables and Request Parameters
     * @param response Response
     * @throws IOException Exception
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
