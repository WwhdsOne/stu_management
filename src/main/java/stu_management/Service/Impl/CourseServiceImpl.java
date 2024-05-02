package stu_management.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu_management.DAO.CourseMapper;
import stu_management.Service.CourseService;
import stu_management.Service.StudentService;
import stu_management.entity.*;
import stu_management.utils.UserHolder;

import java.util.List;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:12
 * @description 课程服务
 **/
@Service
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseDTO> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentService studentService;

    @Override
    public Result addCourse(CourseDTO courseDTO) {
        //插入课程
        boolean save = save(courseDTO);
        if(!save){
            return Result.fail("插入课程失败");
        }
        return Result.ok();
    }

    @Override
    public Result getByUserId() {
        //根据用户id查询课程
        UserDTO userDTO = UserHolder.getUser();
        List<Long> courseById = courseMapper.getCourseById(userDTO.getId());
        log.info("courseById:{}",courseById);
        return Result.ok(courseById);
    }

    @Override
    public Result chooseCourse(StuCourse stu) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getUserId, stu.getStuId());
        Student student = studentService.getOne(studentLambdaQueryWrapper);
        log.info("student:{}",student);
        if(student == null){
            return Result.fail("学生不存在");
        }

        if(student.getStatus() != 1){
            return Result.fail("学生状态异常");
        }
        List<Long> courseById = courseMapper.getCourseById(Long.valueOf(stu.getStuId()));
        if( !courseById.isEmpty() ){
            LambdaQueryWrapper<CourseDTO> courseDTOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            courseDTOLambdaQueryWrapper.in(CourseDTO::getId, courseById);
            List<CourseDTO> selectedCourses = courseMapper.selectList(courseDTOLambdaQueryWrapper);
            int sum = selectedCourses.stream().mapToInt(CourseDTO::getCourseCredit).sum();
            if(sum + courseMapper.selectById(stu.getCourseId()).getCourseCredit() > 18){
                return Result.fail("学分超出限制");
            }
        }
        courseMapper.chooseCourse(stu);
        return Result.ok();
    }
}
