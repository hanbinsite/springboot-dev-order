package site.hb.order.common.result;

public class ResultUtil {

    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    public static <T> Result<T> success(int code, T data) {
        return new Result<>(code, "success", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(1, msg, data);
    }

    public static <T> Result<T> success(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> fail(int code) {
        return new Result<>(code, "fail", null);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(0, msg, null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

}
