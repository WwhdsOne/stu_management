package stu_management.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stu_management.Mapper.CourseMapper;
import stu_management.Service.CourseService;
import stu_management.Service.StudentService;
import stu_management.entity.*;
import stu_management.utils.UserHolder;

import java.util.List;
import java.util.Objects;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:12
 * @description 课程服务
 **/
@Service
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseDTO> implements CourseService {

    private final CourseMapper courseMapper;

    private final StudentService studentService;
    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper, StudentService studentService) {
        this.courseMapper = courseMapper;
        this.studentService = studentService;
    }

    @Override
    public Result addCourse(CourseDTO courseDTO) {
        //插入课程
        boolean save = save(courseDTO);
        if ( !save ) {
            return Result.fail("插入课程失败");
        }
        return Result.ok();
    }

    @Override
    public Result getByUserId() {
        //根据用户id查询课程
        UserDTO userDTO = UserHolder.getUser();
        List<Long> courseById = courseMapper.getCourseById(userDTO.getId());
        log.info("courseById:{}", courseById);
        return Result.ok(courseById);
    }

    @Override
    @Transactional
    public Result chooseCourse(StuCourse stu) {
        // 创建一个学生查询包装器
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 设置查询条件为学生ID
        studentLambdaQueryWrapper.eq(Student::getUserId, stu.getStuId());
        // 从数据库中获取学生信息
        Student student = studentService.getOne(studentLambdaQueryWrapper);
        // 记录学生信息
        log.info("student:{}", student);
        // 如果学生不存在，返回失败结果
        if ( student == null ) {
            return Result.fail("学生不存在");
        }

        // 如果学生状态不为1，返回失败结果
        if ( student.getStatus() != 1 ) {
            return Result.fail("学生状态异常");
        }
        // 根据学生ID获取课程列表
        List<Long> courseById = courseMapper.getCourseById(Long.valueOf(stu.getStuId()));
        // 如果课程列表不为空
        if ( !courseById.isEmpty() ) {
            // 创建一个课程查询包装器
            LambdaQueryWrapper<CourseDTO> courseDTOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            // 设置查询条件为课程ID
            courseDTOLambdaQueryWrapper.in(CourseDTO::getId, courseById);
            // 从数据库中获取已选课程列表
            List<CourseDTO> selectedCourses = courseMapper.selectList(courseDTOLambdaQueryWrapper);
            // 计算课程学分总和
            int sum = selectedCourses.stream().mapToInt(CourseDTO::getCourseCredit).sum();
            // 如果课程学分总和加上待选课程的学分大于18，返回失败结果
            CourseDTO courseDTO1 = courseMapper.selectById(stu.getCourseId());
            if ( sum + courseDTO1.getCourseCredit() > 18 ) {
                return Result.fail("学分超出限制");
            }
            if(courseDTO1.getPrerequisiteId() == null){
                // 为学生选择课程
                courseMapper.chooseCourse(stu);
                // 返回成功结果
                return Result.ok();
            }
            // 检查是否已选先修课程
            boolean hasPrerequisite = selectedCourses.stream().anyMatch(
                    courseDTO -> Objects.equals(courseDTO1.getPrerequisiteId(), courseDTO.getId()));
            // 如果未选先修课程，返回失败结果
            if ( !hasPrerequisite ) {
                return Result.fail("未选择先修课程");
            }
        }
        // 为学生选择课程
        courseMapper.chooseCourse(stu);
        // 添加成绩项
        courseMapper.addScore(stu.getId());
        // 返回成功结果
        return Result.ok();
    }

    @Override
    public Result dropCourse(StuCourse stu) {
        // 根据学生ID获取课程列表
        List<Long> courseById = courseMapper.getCourseById(Long.valueOf(stu.getStuId()));
        if( courseById.isEmpty() ){
            return Result.fail("学生未选课程");
        }
        // 创建一个课程查询包装器
        LambdaQueryWrapper<CourseDTO> courseDTOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 设置查询条件为课程ID
        courseDTOLambdaQueryWrapper.in(CourseDTO::getId, courseById);
        // 从数据库中获取已选课程列表
        List<CourseDTO> selectedCourses = courseMapper.selectList(courseDTOLambdaQueryWrapper);
        // 检查当前课程是否是已选课程的先修课程
        boolean isPrerequisite = selectedCourses.stream().anyMatch(courseDTO -> Objects.equals(stu.getCourseId(), courseDTO.getPrerequisiteId()));
        // 如果当前课程是已选课程的先修课程，返回错误信息
        if (isPrerequisite) {
            return Result.fail("当前课程是已选课程的先修课程，不能删除");
        }
        //获取这门课的成绩
        Integer score = courseMapper.getScore(stu.getStuId(), stu.getCourseId());
        if(score != null){
            return Result.fail("当前课程已取得成绩，无法退课");
        }
        //删除课程成绩记录
        courseMapper.dropScore(stu);
        // 删除课程
        courseMapper.dropCourse(stu);
        return Result.ok();
    }

    @Override
    public Result allStuCourse() {
        // 获取所有学生课程
        List<StuCourseVO> allStuCourse = courseMapper.allStuCourse();
        if(allStuCourse == null || allStuCourse.isEmpty()){
            return Result.fail("没有学生选课");
        }
        return Result.ok(allStuCourse);
    }

    @Override
    public Result updateScore(StuCourseVO stuCourseVO) {
        // 更新学生成绩
        try {
            courseMapper.updateScore(stuCourseVO);
        } catch (Exception e) {
            return Result.fail("更新学生成绩失败");
        }
        return Result.ok();
    }
}
