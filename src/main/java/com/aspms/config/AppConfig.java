package com.aspms.config;

import com.aspms.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;

/**
 * 系统配置
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 指定用户登录拦截器，并配置拦截
        registry.addInterceptor(loginInterceptor).
                // 拦截根路径及所有url
                        addPathPatterns("/**").
                // 不拦截登录注册接口
                        excludePathPatterns("/api/user/login.do").
                excludePathPatterns("/api/user/register.do").
                // 不拦截登录注册页面
                        excludePathPatterns("/userLogin").
                excludePathPatterns("/userRegister").
                // 不拦截静态资源
                        excludePathPatterns("/welcome").
                excludePathPatterns("/img/**").
                excludePathPatterns("/tinymce/**").
                excludePathPatterns("/static/**");
    }

}
