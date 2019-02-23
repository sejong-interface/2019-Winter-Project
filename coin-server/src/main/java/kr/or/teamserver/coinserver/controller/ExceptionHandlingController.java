package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.exception.FileNotFoundException;
import kr.or.teamserver.coinserver.exception.FileStorageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({FileNotFoundException.class, FileStorageException.class})
    public void conflict() {

    }
}
