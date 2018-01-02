package com.zyl.ssm.handler;

import org.apache.log4j.Logger;
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
	private Logger LOGGER = Logger.getLogger(getClass());
    /**
     * 处理自定义异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public User exceptionHandler(CustomException exception){
    	LOGGER.error("CustomException:"+exception.getMessage(),exception);
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
        
    	LOGGER.error("exceptionHandler:"+exception.getMessage(),exception);
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
    	LOGGER.error("throwable:"+throwable.getMessage(),throwable);
        return new User(1,throwable.getMessage(),0);
    }
    
}
