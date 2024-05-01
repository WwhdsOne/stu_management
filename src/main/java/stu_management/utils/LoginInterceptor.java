package stu_management.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import stu_management.entity.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //1. 判断是否需要拦截(ThreadLocal中是否存在用户信息)
        UserDTO userDTO = UserHolder.getUser();
        if(userDTO == null){
            response.setStatus(401);
            return false;
        }
        //有用户信息，放行
        return true;
    }
}
