package stu_management.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stu_management.Service.CourseService;
import stu_management.Service.StudentService;
import stu_management.entity.Result;
import stu_management.entity.StuCourseVO;
import stu_management.entity.Student;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/3 下午5:51
 * @description 学院控制器
 **/
@RestController
@RequestMapping("/college")
@Slf4j
public class CollegeController {

    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    public CollegeController(StudentService studentService,CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
    @PostMapping("/addStudent")
    public Result addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/allStuCourse")
    public Result allStuCourse() {
        return courseService.allStuCourse();
    }

    @PostMapping("/allCourses")
    public Result allCourse() {
        return Result.ok(courseService.list());
    }

    @PostMapping("/allStudents")
    public Result allStudent() {
        return studentService.getStudents();
    }

    @PostMapping("/updateScore")
    public Result updateScore(@RequestBody StuCourseVO stuCourseVO) {
        return courseService.updateScore(stuCourseVO);
    }
}
