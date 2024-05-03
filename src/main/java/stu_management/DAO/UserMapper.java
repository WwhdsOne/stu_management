package stu_management.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu_management.entity.Student;
import stu_management.entity.UserDTO;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/3 下午5:54
 * @description 用户映射
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserDTO> {
}
