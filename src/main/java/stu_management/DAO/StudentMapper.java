package stu_management.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu_management.entity.Student;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:23
 * @description 学生映射
 **/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
