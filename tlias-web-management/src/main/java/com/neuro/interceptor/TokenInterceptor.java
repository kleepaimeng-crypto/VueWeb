package com.neuro.interceptor;

import com.neuro.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String requestURI = request.getRequestURI() ;// 获取请求的URI
//
//        if (requestURI.contains("/login"))
//        {
//            log.info("登录请求，放行");
//            return true;
//        }

        String token = request.getHeader("token");//从请求头中获取token
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，拒绝访问");
            response.setStatus(401);
            return false;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法，拒绝访问");
            response.setStatus(401);
            return false;
        }

        log.info("令牌合法，放行");
        return true;
    }
}
