package kr.or.teamserver.coinserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")  // 404
public class DeviceNotFound extends RuntimeException {

    public DeviceNotFound(String message) {
        super(message);
    }
}