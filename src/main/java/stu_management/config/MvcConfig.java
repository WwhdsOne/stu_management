package stu_management.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import stu_management.utils.LoginInterceptor;
import stu_management.utils.RefreshTokenInterceptor;

import javax.annotation.Resource;

/**
 * @author Wwh
 * @ProjectName hm-dianping
 * @dateTime 2024/4/6 下午8:41
 * @description MVC配置
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/**"
                ).order(1);
        //刷新token拦截器
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**").order(0);
    }
}
