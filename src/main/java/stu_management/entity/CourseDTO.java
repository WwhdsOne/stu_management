package stu_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("course")
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type=IdType.AUTO)
    private Integer id;

    @TableField("courseName")
    private String courseName;

    @TableField("classroom")
    private String classroom;

    @TableField("teacher")
    private String teacher;

    @TableField("classTime")
    private LocalDateTime classTime;

    @TableField("prerequisiteId")
    private Integer prerequisiteId;

    @TableField("courseCredit")
    private Integer courseCredit;

    @TableField("courseDescription")
    private String courseDescription;
}
