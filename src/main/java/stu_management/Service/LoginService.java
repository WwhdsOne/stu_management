package stu_management.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import stu_management.entity.LoginForm;
import stu_management.entity.UserDTO;
import stu_management.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/4/30 上午11:04
 * @description 登录服务
 **/

public interface LoginService extends IService<UserDTO> {
    /**
     * 登录
     * @param loginForm 登录表单
     * @return 登录结果
     */
    Result login(LoginForm loginForm);

    Result updatePassword(String password);
}
