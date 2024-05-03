package stu_management.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import stu_management.entity.*;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:11
 * @description 课程服务
 **/
public interface CourseService extends IService<CourseDTO> {
    Result addCourse(CourseDTO courseDTO);

    Result getByUserId();

    Result chooseCourse(StuCourse stu);

    Result dropCourse(StuCourse stu);

    Result allStuCourse();

    Result updateScore(StuCourseVO stuCourseVO);
}
