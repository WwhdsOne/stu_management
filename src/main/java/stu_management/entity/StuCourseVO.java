package stu_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/3 下午7:21
 * @description 学生课程
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuCourseVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String studentName;
    private String courseName;
    private Integer score;
}
