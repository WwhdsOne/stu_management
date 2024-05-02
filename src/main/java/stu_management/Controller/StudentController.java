package stu_management.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu_management.Service.CourseService;
import stu_management.entity.Result;
import stu_management.entity.StuCourse;
import stu_management.entity.Student;
import stu_management.entity.UserDTO;

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

    @Autowired
    private CourseService courseService;

    @PostMapping("/getStuCourse")
    public Result getStuCourse() {
        return courseService.getByUserId();
    }

    @PostMapping("/courses")
    public Result getAllCourses() {
        return Result.ok(courseService.list());
    }

    @PostMapping("/choose")
    public Result chooseCourse(@RequestBody StuCourse stu) {
        return courseService.chooseCourse(stu);
    }
}
