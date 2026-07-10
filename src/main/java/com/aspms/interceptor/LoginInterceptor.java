package com.aspms.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 系统登录 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 登录拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取HttpSession对象
        HttpSession session = request.getSession(false);
        // 判断当前是否有用户登录
        if (session != null && session.getAttribute("loginUser") != null) {
            // 有用户登录
            return true;
        }
        // 未有用户登录，直接跳转到登录页面
        response.sendRedirect("/userLogin");
        return false;
    }

}
