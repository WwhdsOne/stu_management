package stu_management.Service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stu_management.Mapper.LoginMapper;
import stu_management.entity.LoginForm;
import stu_management.entity.UserDTO;
import stu_management.entity.Result;
import stu_management.entity.UserTokenInfo;
import stu_management.Service.LoginService;
import stu_management.utils.UserHolder;
import sun.security.util.Password;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static stu_management.Constants.RedisConstants.LOGIN_USER_KEY;
import static stu_management.Constants.RedisConstants.LOGIN_USER_TTL;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/4/30 上午11:04
 * @description 登录服务实现
 **/
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, UserDTO> implements LoginService {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public LoginServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Result login(LoginForm loginForm) {
        // 创建一个 LambdaQueryWrapper 对象，用于构建查询条件
        LambdaQueryWrapper<UserDTO> queryWrapper = new LambdaQueryWrapper<>();

        // 获取用户名
        String username = loginForm.getUsername();
        // 获取密码
        String password = loginForm.getPassword();

        // 添加查询条件，用户名必须等于输入的用户名
        queryWrapper.eq(UserDTO::getUsername, username);

        // 添加查询条件，密码必须等于输入的密码
        queryWrapper.eq(UserDTO::getPassword, password);

        // 根据查询条件从数据库中获取一个用户对象
        UserDTO userDTO = getOne(queryWrapper);

        // 如果用户对象为空，说明用户名或密码错误，返回错误信息
        if ( userDTO == null ) {
            return Result.fail("用户名或密码错误");
        }

        // 生成一个随机的 token
        String token = UUID.randomUUID().toString(true);

        // 将用户对象转换为 Map 对象，忽略空值，并将所有值转换为字符串
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(
                userDTO, new HashMap<>(),
                CopyOptions.create()
                .setIgnoreNullValue(true)
                .setFieldValueEditor((value, prop) -> prop.toString())
        );
        // 将用户信息存入 Redis，键为 "LOGIN_USER_KEY + token"，值为用户信息的 Map 对象
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, stringObjectMap);

        // 设置 Redis 中用户信息的过期时间
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);

        UserTokenInfo userTokenInfo = new UserTokenInfo();
        userTokenInfo.setToken(token);
        userTokenInfo.setRole(userDTO.getRole());
        // 返回 token
        return Result.ok(userTokenInfo);
    }

    @Override
    @Transactional
    public Result updatePassword(String password) {
        UserDTO user = UserHolder.getUser();
        if(password.isEmpty() || password.length() > 15){
            return Result.fail("密码不能为空或长度不能超过15位");
        }
        user.setPassword(password);
        boolean b = updateById(user);
        if( b ){
            return Result.ok("密码修改成功");
        }
        return Result.fail("密码修改失败");
    }
}
