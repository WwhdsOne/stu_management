package stu_management.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu_management.Service.CourseService;
import stu_management.entity.Result;
import stu_management.entity.StuCourse;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/2 下午7:23
 * @description 学生
 **/
@RestController
@RequestMapping("/stu")
@Slf4j
public class StudentController {

    private final CourseService courseService;
    @Autowired
    public StudentController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 获取学生课程
     * @return Result
     */
    @PostMapping("/getStuCourse")
    public Result getStuCourse() {
        return courseService.getByUserId();
    }

    /**
     * 获取所有课程
     * @return Result
     */
    @PostMapping("/courses")
    public Result getAllCourses() {
        return Result.ok(courseService.list());
    }

    /**
     * 选课
     * @param stu
     * @return Result
     */
    @PostMapping("/choose")
    public Result chooseCourse(@RequestBody StuCourse stu) {
        return courseService.chooseCourse(stu);
    }

    /**
     * 退课
     * @param stu
     * @return Result
     */
    @PostMapping("/drop")
    public Result dropCourse(@RequestBody StuCourse stu){
        return courseService.dropCourse(stu);
    }
}
