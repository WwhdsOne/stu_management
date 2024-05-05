package stu_management.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu_management.Service.CourseService;
import stu_management.Service.StudentService;
import stu_management.entity.CourseDTO;
import stu_management.entity.Result;
import stu_management.entity.Student;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午6:26
 * @description 教务处
 **/
@RestController
@RequestMapping("/eduOffice")
@Slf4j
public class EducationalOfficeController {

    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    public EducationalOfficeController(StudentService studentService,CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * 添加学生
     * @param courseDTO
     * @return Result
     */
    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody CourseDTO courseDTO) {
        log.info("courseDTO: {}", courseDTO);
        return courseService.addCourse(courseDTO);
    }

    /**
     * 获取所有学生
     * @return Result
     */
    @PostMapping("/students")
    public Result students() {
        return studentService.getStudents();
    }

    /**
     * 更新学生信息
     * @param student
     * @return Result
     */
    @PostMapping("/updateStudent")
    public Result updateById(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }
}
