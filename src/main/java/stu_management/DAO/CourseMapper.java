package stu_management.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import stu_management.entity.CourseDTO;
import stu_management.entity.StuCourse;

import java.util.List;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午7:44
 * @description 课程映射器
 **/
@Mapper
public interface CourseMapper extends BaseMapper<CourseDTO> {

    @Select("select course_id from stu_course where user_id = #{userId}")
    List<Long> getCourseById(Long userId);

    @Insert("insert into stu_course(user_id,course_id) value (#{stuId},#{courseId})")
    void chooseCourse(StuCourse stu);

    @Delete("delete from stu_course where user_id = #{stuId} and course_id = #{courseId}")
    void dropCourse(StuCourse stu);
}
