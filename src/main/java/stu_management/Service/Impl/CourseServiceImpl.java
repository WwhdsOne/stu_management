package stu_management.Service.Impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import stu_management.DAO.CourseMapper;
import stu_management.Service.CourseService;
import stu_management.entity.CourseDTO;
import stu_management.entity.Result;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/1 下午8:12
 * @description 课程服务
 **/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseDTO> implements CourseService {

    @Override
    public Result addCourse(CourseDTO courseDTO) {
        //插入课程
        boolean save = save(courseDTO);
        if(!save){
            return Result.fail("插入课程失败");
        }
        return Result.ok();
    }
}
