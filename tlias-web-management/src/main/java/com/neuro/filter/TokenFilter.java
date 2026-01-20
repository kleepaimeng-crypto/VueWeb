package com.neuro.filter;

import com.neuro.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;//转换为HttpServletRequest对象
        HttpServletResponse response = (HttpServletResponse) servletResponse;//转换为HttpServletResponse对象

        String requestURI = request.getRequestURI() ;// 获取请求的URI

        if (requestURI.contains("/login"))
        {
            log.info("登录请求，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = request.getHeader("token");//从请求头中获取token
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，拒绝访问");
            response.setStatus(401);
            return;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法，拒绝访问");
            response.setStatus(401);
            return;
        }

        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
