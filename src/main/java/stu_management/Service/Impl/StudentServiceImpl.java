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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    private UserMapper userMapper;


    @Autowired
    public StudentServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result getStudents() {
        // 获取所有学生列表
        List<Student> students = list();
        // 如果学生列表为空，返回失败结果
        if ( students == null || students.isEmpty() ) {
            return Result.fail("没有学生");
        }
        // 记录学生信息
        log.info("students: {}", students);
        // 返回成功结果，包含学生列表
        return Result.ok(students);
    }

    @Override
    public Result updateStudent(Student student) {
        // 更新学生信息，如果更新成功返回true，否则返回false
        boolean b = updateById(student);
        // 如果更新成功，返回成功结果
        if ( b ) {
            return Result.ok("更新成功");
        }
        // 如果更新失败，返回失败结果
        return Result.fail("更新失败");
    }

    @Override
    @Transactional
    public Result addStudent(Student student) {
        // 创建一个新的UserDTO对象
        UserDTO userDTO = new UserDTO();
        // 设置用户名为学生编号
        userDTO.setUsername(student.getStudentNo());
        // 设置默认密码
        userDTO.setPassword("123456");
        // 设置角色为4（假设4代表学生角色）
        userDTO.setRole(4);
        // 插入新的用户记录，返回插入的记录数
        int insert = userMapper.insert(userDTO);
        // 如果插入成功
        if ( insert > 0 ) {
            // 设置学生的用户ID为新插入的用户记录的ID
            student.setUserId(Math.toIntExact(userDTO.getId()));
            // 保存学生信息，如果保存成功返回true，否则返回false
            boolean save = save(student);
            // 如果保存成功，返回成功结果
            if ( save ) {
                return Result.ok("添加成功");
            }
        }
        // 如果添加失败，返回失败结果
        return Result.fail("添加失败");
    }

}
