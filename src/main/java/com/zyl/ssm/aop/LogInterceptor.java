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

//@Order(1)
//@Component
//@Aspect
public class LogInterceptor {
	private Logger LOGGER = Logger.getLogger(getClass());
	/**
	 * 声明切入点
	 */
//	@Pointcut(value="excution(public com.zyl.ssm.api..*Controller.*(..))")
	
	@Pointcut(value="within(com.zyl.ssm.api.*)")
//	@Pointcut(value="")
	public void logPointCut(){}
	
	@Before("logPointCut()")
	public void doBefore(JoinPoint joinpoint) {
		LOGGER.info("doBefore:"+Arrays.toString(joinpoint.getArgs()));
	}
	
	@Around("logPointCut()")
	public Object doAround(ProceedingJoinPoint joinpoint) throws Throwable{
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object proceed;		
		LOGGER.info("doAround start");
		if(checkPermission(httpServletRequest)){
			proceed = joinpoint.proceed();
		}else{
			LOGGER.warn("权限验证失败");
			proceed = new User(1,"权限验证失败",0);
		}
		LOGGER.info("dorAround end");
		
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
	public void doAfter(JoinPoint joinpoint) {
		LOGGER.info("doAfter");
	}
	
	@AfterReturning(pointcut="logPointCut()",returning="retVal")
	public void doAfterReturning(JoinPoint joinpoint,Object retVal){
		LOGGER.info("doAfterReturning result:"+retVal);
	}
	
	@AfterThrowing(pointcut="logPointCut()",throwing="throwable")
	public void doAfterThrowing(JoinPoint joinPoint,Throwable throwable){
		LOGGER.error("doAfterThrowing"+ joinPoint.getSignature().toShortString() + "exception:"+throwable.getMessage(),throwable);
	}
	
}
