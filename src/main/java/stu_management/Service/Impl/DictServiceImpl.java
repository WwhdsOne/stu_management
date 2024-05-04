package stu_management.Service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import stu_management.Mapper.DictMapper;
import stu_management.Service.DictService;
import stu_management.entity.Dict;
import stu_management.entity.Result;
import stu_management.entity.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static stu_management.Constants.RedisConstants.LOGIN_USER_KEY;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/2 下午4:38
 * @description DictServiceImpl
 **/
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result getDictJson() {
        List<Dict> list = list();
        if (list == null || list.isEmpty()) {
            return Result.fail("获取字典失败");
        }
        Map<String, List<Dict>> groupedDicts = list.stream().collect(Collectors.groupingBy(Dict::getType));
        String jsonStr = JSONUtil.toJsonStr(groupedDicts);
        return Result.ok(jsonStr);
    }

    @Override
    public Result getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        Map<Object, Object> user = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
        if (user.isEmpty()) {
            return Result.fail("用户信息不存在");
        }
        UserDTO userDTO = BeanUtil.fillBeanWithMap(user, new UserDTO(), false);
        return Result.ok(userDTO);
    }
}
