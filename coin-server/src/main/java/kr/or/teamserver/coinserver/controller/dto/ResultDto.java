package kr.or.teamserver.coinserver.controller.dto;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class ResultDto<T> {

    private final int code;
    private final String message;
    private final List<T> items;

    public ResultDto(int code, String message, List<T> items) {
        this.code = code;
        this.message = message;
        this.items = items;
    }

    public static <T> ResultDto succeed(T data) {
        return new ResultDto(200, "정상적으로 처리되었습니다.", List.of(data));
    }

    public static <T> ResultDto from(List<T> washers) {
        // TODO 메세지에 대한 것은 message source 를 이용한 처리로 전환해야 함
        if (CollectionUtils.isEmpty(washers)) {
            return new ResultDto(404, "데이터가 존재하지 않습니다.", washers);
        } else {
            return new ResultDto(200, "정상적으로 처리되었습니다.", washers);
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", items=" + items +
                "}";
    }
}
