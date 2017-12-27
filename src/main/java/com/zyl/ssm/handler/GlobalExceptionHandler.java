package com.zyl.ssm.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zyl.ssm.exception.CustomException;

/**
 * 统一处理异常
 * @author Administrator
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 处理自定义异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String exceptionHandler(CustomException exception){
        
        
        return exception.getMessage();
    }
    
    /**
     * 处理空指针异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String exceptionHandler(NullPointerException exception){
        
        
        return exception.getMessage();
    }
    
    /**
     * 其他未处理的异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public String exceptionHandler(Throwable throwable){
        
        
        return throwable.getMessage();
    }
    
}
