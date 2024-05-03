package stu_management.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import stu_management.entity.Result;
import stu_management.entity.StuCourseVO;
import stu_management.entity.Student;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:14
 * @description 学生服务
 **/
public interface StudentService extends IService<Student> {
    Result getStudents();

    Result updateStudent(Student student);

    Result addStudent(Student student);

}
