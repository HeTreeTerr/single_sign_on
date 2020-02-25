package hss.sso.jwt.exception;

import hss.sso.jwt.entity.ReturnEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ReturnEntity handleException(Exception e) {
        e.printStackTrace();
        if (e instanceof CustomException) {
            return ReturnEntity.failedResult(e.getMessage());
        } else {
            return ReturnEntity.failedResult("未知错误：" + e.getMessage());
        }
    }
}
