package site.hb.order.common.exception.handler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import site.hb.order.common.exception.base.CustomException;
import site.hb.order.common.result.Result;
import site.hb.order.common.result.ResultUtil;

public class GlobalExceptionHandler {

    // 新增：处理校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResultUtil.fail(400, "参数校验失败: " + String.join(", ", errors.values()));
    }

    // 处理自定义业务异常
    @ExceptionHandler(CustomException.class)
    public Result<String> handleBusinessException(CustomException e) {
        return ResultUtil.error(e.getMessage());
    }

    // 处理其他特定异常示例（如权限异常）
    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDenied(AccessDeniedException e) {
        return ResultUtil.fail(403, "权限不足: " + e.getMessage());
    }
    
    // 处理其他所有异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return ResultUtil.error(e.getMessage());
    }

}
