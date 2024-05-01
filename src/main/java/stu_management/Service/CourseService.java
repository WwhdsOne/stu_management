package stu_management.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import stu_management.entity.CourseDTO;
import stu_management.entity.Result;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:11
 * @description 课程服务
 **/
public interface CourseService extends IService<CourseDTO> {
    Result addCourse(CourseDTO courseDTO);
}
