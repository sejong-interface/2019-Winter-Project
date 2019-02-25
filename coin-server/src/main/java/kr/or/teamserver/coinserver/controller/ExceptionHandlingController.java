package kr.or.teamserver.coinserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.or.teamserver.coinserver.exception.DeviceNotFound;
import kr.or.teamserver.coinserver.exception.FileNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order")  // 404
    @ExceptionHandler({ DeviceNotFound.class, FileNotFoundException.class })
    public String conflict() {
        logger.error("Exception Error");
        return "Cannot Found File";
    }
}
