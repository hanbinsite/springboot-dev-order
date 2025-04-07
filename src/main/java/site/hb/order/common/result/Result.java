package site.hb.order.common.result;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    private LocalDateTime time;


    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = LocalDateTime.now();
    }

}
