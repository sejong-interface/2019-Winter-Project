package kr.or.teamserver.coinserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DeviceNotFound extends RuntimeException {

    public DeviceNotFound(String message) {
        super(message);
    }
}
