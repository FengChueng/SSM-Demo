package com.zyl.ssm.interceptor;

import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WebInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        ServletContext servletContext = request.getServletContext();
//        String servletServerInfo = servletContext.getServerInfo();//servletServerInfo:Apache Tomcat/9.0.0.M17
//        System.out.println("servleetServerInfo:"+servletServerInfo);
//        String servletContextName = servletContext.getServletContextName();//servletContextName:null
//        System.out.println("servletContextName:"+servletContextName);
//        String virtualServerName = servletContext.getVirtualServerName();//virtualServerName:Catalina/localhost
//        System.out.println("virtualServerName:"+virtualServerName);
//        
//        StringBuffer requestURL = request.getRequestURL();//http://127.0.0.1:8080/SSM-Demo/user/hello/10/addUser.json
//        System.out.println("requestURL:"+requestURL);
//        String requestURI = request.getRequestURI();///SSM-Demo/user/hello/10/addUser.json
//        System.out.println("requestURI:"+requestURI);
//        
//        String servletContextPath = servletContext.getContextPath();//servletContextPath:/SSM-Demo
//        System.out.println("servletContextPath:"+servletContextPath);
//        String contextPath = request.getContextPath();//contextPath:/SSM-Demo
//        System.out.println("contextPath:"+contextPath);
//        String serverName = request.getServerName();//serverName:127.0.0.1
//        System.out.println("serverName:"+serverName);
//        String localAddr = request.getLocalAddr();//localAddr:127.0.0.1
//        System.out.println("localAddr:"+localAddr);
//        String remoteHost = request.getRemoteHost();//remoteHost:127.0.0.1
//        System.out.println("remoteHost:"+remoteHost);
//        String remoteAddr = request.getRemoteAddr();//remoteAddr:127.0.0.1
//        System.out.println("remoteAddr:"+remoteAddr);
//        
//        String servletPath = request.getServletPath();//servletPath:/user/hello/10/addUser.json
//        System.out.println("servletPath:"+servletPath);
//        
//        int localPort = request.getLocalPort();//localPort:8080
//        System.out.println("localPort:"+localPort);
//        int remotePort = request.getRemotePort();//remotePort:54903
//        System.out.println("remotePort:"+remotePort);
//        int serverPort = request.getServerPort();//serverPort:8080
//        System.out.println("serverPort:"+serverPort);
//        
//        
//        String characterEncoding = request.getCharacterEncoding();//characterEncoding:UTF-8
//        System.out.println("characterEncoding:"+characterEncoding);
//        String authType = request.getAuthType();//authType:null
//        System.out.println("authType:"+authType);
//        String contentType = request.getContentType();//contentType:null
//        System.out.println("contentType:"+contentType);
//        
//        String scheme = request.getScheme();//scheme:http
//        System.out.println("scheme:"+scheme);
//        
//        String method = request.getMethod();//method:GET
//        System.out.println("method:"+method);
//        
//        String requestedSessionId = request.getRequestedSessionId();//requestedSessionId:null
//        System.out.println("requestedSessionId:"+requestedSessionId);
//
//        String pathInfo = request.getPathInfo();//pathInfo:null
//        System.out.println("pathInfo:"+pathInfo);
//        String pathTranslated = request.getPathTranslated();//pathTranslated:null
//        System.out.println("pathTranslated:"+pathTranslated);
//
//        String localName = request.getLocalName();//localName:practivate.adobe.com
//        System.out.println("localName:"+localName);
//
//        String remoteUser = request.getRemoteUser();//remoteUser:null
//        System.out.println("remoteUser:"+remoteUser);
    	
//    	String protocol = request.getProtocol();
//    	System.out.println(protocol);
//    	
//        Enumeration<String> headerNames2 = request.getHeaderNames();
//        System.out.println("--------------headerNames  start--------------");
//        while (headerNames2.hasMoreElements()) {
//            System.out.println(headerNames2.nextElement()); 
//            
//        }
//        System.out.println("--------------headerNames  end--------------");
//        
//        Enumeration<String> parameterNames2 = request.getParameterNames();
//        System.out.println("--------------parameterNames  start--------------");
//        while (parameterNames2.hasMoreElements()) {
//            System.out.println(parameterNames2.nextElement()); 
//            
//        }
//        System.out.println("--------------parameterNames  end--------------");
//        
//        Enumeration<String> attributeNames = request.getAttributeNames();
//        System.out.println("--------------attributeName  start--------------");
//        while (attributeNames.hasMoreElements()) {
//            System.out.println(attributeNames.nextElement()); 
//            
//        }
//        System.out.println("--------------attributeName  end--------------");
        
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
}
