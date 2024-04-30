package stu_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu_management.DTO.Result;
import stu_management.Service.LoginService;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/4/30 上午11:02
 * @description 登录控制器
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(String username, String password) {
        return loginService.login(username, password);
    }
}
