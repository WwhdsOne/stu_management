package stu_management.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stu_management.entity.LoginForm;
import stu_management.entity.Result;
import stu_management.Service.LoginService;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/4/30 上午11:02
 * @description 登录控制器
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {

    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    /**
     * 登录
     * @param loginForm
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
        log.info("loginForm: {}", loginForm);
        return loginService.login(loginForm);
    }
}
