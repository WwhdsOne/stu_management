package stu_management.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu_management.entity.UserDTO;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/4/30 上午11:08
 * @description 登录Mapper
 **/
@Mapper
public interface LoginMapper extends BaseMapper<UserDTO> {
}
