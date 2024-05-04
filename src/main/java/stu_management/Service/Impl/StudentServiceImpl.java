package stu_management.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stu_management.Mapper.StudentMapper;
import stu_management.Mapper.UserMapper;
import stu_management.Service.StudentService;
import stu_management.entity.Result;
import stu_management.entity.Student;
import stu_management.entity.UserDTO;

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

    @Autowired
    private UserMapper userMapper;

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

    @Override
    @Transactional
    public Result addStudent(Student student) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(student.getStudentNo());
        userDTO.setPassword("123456");
        userDTO.setRole(4);
        int insert = userMapper.insert(userDTO);
        if(insert > 0){
            student.setUserId(Math.toIntExact(userDTO.getId()));
            boolean save = save(student);
            if(save){
                return Result.ok("添加成功");
            }
        }
        return Result.fail("添加失败");
    }

}
