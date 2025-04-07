package site.hb.order.common.result;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Result<T> {

    /**
     * 返回状态码: 0=失败， 1=成功，其他自定义
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据主体
     */
    private T data;

    /**
     * 当前服务器时间
     */
    private LocalDateTime time;


    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = LocalDateTime.now();
    }

}
