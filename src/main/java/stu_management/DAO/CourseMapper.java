package stu_management.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu_management.entity.CourseDTO;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午7:44
 * @description 课程映射器
 **/
@Mapper
public interface CourseMapper extends BaseMapper<CourseDTO> {
}
