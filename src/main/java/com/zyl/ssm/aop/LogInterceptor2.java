package com.zyl.ssm.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zyl.ssm.pojo.User;

@Order(2)
@Component
@Aspect
public class LogInterceptor2 {
	private Logger LOGGER = Logger.getLogger(getClass());
	/**
	 * 声明切入点
	 */
//	@Pointcut(value="excution(public com.zyl.ssm.api..*Controller.*(..))")
	
	@Pointcut(value="within(com.zyl.ssm.api.*)")
//	@Pointcut(value="")
	public void logPointCut(){}
	
	@Before("logPointCut()")
	public void doBefore(JoinPoint joinpoint){
		LOGGER.info("doBefore2:"+Arrays.toString(joinpoint.getArgs()));
	}
	
	@Around("logPointCut()")
	public Object doAround(ProceedingJoinPoint joinpoint) throws Throwable{
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object proceed;		
		LOGGER.info("doAround2 start");
		if(checkPermission(httpServletRequest)){
			proceed = joinpoint.proceed();
		}else{
			proceed = new User(1,"权限验证失败",0);
		}
		LOGGER.info("dorAround2 end");
		return proceed;
	}
	
	private boolean checkPermission(HttpServletRequest httpServletRequest) {
		String userName = httpServletRequest.getHeader("token");
		if(userName.equals("123")){
			return true;
		}
		return false;
	}
	
	@After("logPointCut()")
	public void doAfter(JoinPoint joinpoint){
		LOGGER.info("doAfter2");
	}
	
	@AfterReturning(pointcut="logPointCut()",returning="retVal")
	public void doAfterReturning(JoinPoint joinpoint,Object retVal){
		LOGGER.info("doAfterReturning2 result:"+retVal);
	}
	
	@AfterThrowing(pointcut="logPointCut()",throwing="throwable")
	public void doAfterThrowing(JoinPoint joinPoint,Throwable throwable){
		LOGGER.warn("doAfterThrowing2"+ joinPoint.getSignature().toShortString() + "exception:"+throwable.getMessage());
	}
	
}
