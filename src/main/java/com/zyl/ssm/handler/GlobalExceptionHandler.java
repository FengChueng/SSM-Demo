package com.zyl.ssm.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zyl.ssm.exception.CustomException;
import com.zyl.ssm.pojo.User;

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
    public User exceptionHandler(CustomException exception){
        
        
    	System.out.println("CustomException:"+exception.getMessage());
    	return new User();
    }
    
    /**
     * 处理空指针异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public User exceptionHandler(NullPointerException exception){
        
    	System.out.println("exceptionHandler:"+exception.getMessage());
    	return new User();
    }
    
    /**
     * 其他未处理的异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public User exceptionHandler(Throwable throwable){
        System.out.println("throwable:"+throwable.getMessage());
        return new User(1,throwable.getMessage(),0);
    }
    
}
