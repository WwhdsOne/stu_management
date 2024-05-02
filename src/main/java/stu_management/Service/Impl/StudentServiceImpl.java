package stu_management.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stu_management.DAO.StudentMapper;
import stu_management.Service.StudentService;
import stu_management.entity.Result;
import stu_management.entity.Student;

import java.util.List;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:20
 * @description 学生服务
 **/
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

    @Override
    public Result getStudents() {
        List<Student> students = list();
        if(students == null || students.isEmpty() ){
            return Result.fail("没有学生");
        }
        log.info("students: {}", students);
        return Result.ok(students);
    }

    @Override
    public Result updateStudent(Student student) {
        boolean b = updateById(student);
        if(b){
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }
}
