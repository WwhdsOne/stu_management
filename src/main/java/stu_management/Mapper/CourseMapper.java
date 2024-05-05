package stu_management.Mapper;

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


    void chooseCourse(StuCourse stu);

    @Delete("delete from stu_course where stu_id = #{stuId} and course_id = #{courseId}")
    void dropCourse(StuCourse stu);

    @Select("SELECT sc.id AS id,s.name AS student_name, c.courseName AS course_name, sc.score AS score\n" +
            "FROM stu_course g\n" +
            "JOIN students s ON g.stu_id = s.user_id\n" +
            "JOIN course c ON g.course_id = c.id\n" +
            "JOIN stu_course_score sc ON g.id = sc.stu_course_id\n" +
            "WHERE g.stu_id = s.user_id AND g.course_id = c.id")
    List<StuCourseVO> allStuCourse();

    @Update("update stu_course_score set score = #{score} where id = #{id}")
    void updateScore(StuCourseVO stuCourseVO);

    @Insert("insert into stu_course_score(stu_course_id,score) value (#{id},null)")
    void addScore(Integer id);


    Integer getScore(@Param("stuId") Integer stuId, @Param("courseId") Integer courseId);

    void dropScore(StuCourse stu);
}
