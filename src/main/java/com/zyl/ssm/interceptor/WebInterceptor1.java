package com.zyl.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebInterceptor1 extends HandlerInterceptorAdapter {
	private Logger LOGGER = Logger.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LOGGER.info("web interceptor1 preHandle");
        return super.preHandle(request, response, handler);
    }

    public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
            Object paramObject, ModelAndView paramModelAndView) throws Exception {
    	LOGGER.info("web interceptor1 postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	LOGGER.info("web interceptor1 afterCompletion");
    }

}
