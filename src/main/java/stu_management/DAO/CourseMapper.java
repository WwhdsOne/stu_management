package stu_management.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import stu_management.entity.CourseDTO;
import stu_management.entity.StuCourse;
import stu_management.entity.StuCourseVO;

import java.util.List;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午7:44
 * @description 课程映射器
 **/
@Mapper
public interface CourseMapper extends BaseMapper<CourseDTO> {

    @Select("select course_id from stu_course where stu_id = #{userId}")
    List<Long> getCourseById(Long userId);

    @Insert("insert into stu_course(stu_id,course_id) value (#{stuId},#{courseId})")
    void chooseCourse(StuCourse stu);

    @Delete("delete from stu_course where stu_id = #{stuId} and course_id = #{courseId}")
    void dropCourse(StuCourse stu);

    @Select("SELECT sc.id AS id,s.name AS student_name, c.courseName AS course_name, sc.score AS score\n" +
            "FROM stu_course g\n" +
            "JOIN students s ON g.stu_id = s.user_id\n" +
            "JOIN course c ON g.course_id = c.id\n" +
            "JOIN stu_course_score sc ON g.id = sc.stu_score_id\n" +
            "WHERE g.stu_id = s.user_id AND g.course_id = c.id")
    List<StuCourseVO> allStuCourse();

    @Update("update stu_course_score set score = #{score} where id = #{id}")
    void updateScore(StuCourseVO stuCourseVO);
}
