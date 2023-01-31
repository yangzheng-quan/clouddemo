package com.yzq.springCloudDemo.advice;

import com.yzq.springCloudDemo.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handleException(HttpServletRequest req,Exception ex){

        CommonResponse<String> response = new CommonResponse<>(-1,"business error");
        response.setData(ex.getMessage());
        log.error("springCloudDemo service has error: [{}]", ex.getMessage(),ex);
        return response;
    }
}
