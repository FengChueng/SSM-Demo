package com.zyl.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("web interceptor preHandle");
        return super.preHandle(request, response, handler);
    }

    public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
            Object paramObject, ModelAndView paramModelAndView) throws Exception {
        System.out.println("web interceptor postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("web interceptor afterCompletion");
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("web interceptor afterConcurrentHandlingStarted");
    }

}
