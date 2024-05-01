package stu_management.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.LocalDateTypeHandler;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("students")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("student_no")
    private String studentNo;

    @TableId("user_id")
    private Integer userId;

    @TableField("age")
    private Integer age;

    @TableField("gender")
    private String gender;

    @TableField("name")
    private String name;

    @TableField(value = "enrollment_date", typeHandler = LocalDateTypeHandler.class)
    private LocalDate enrollmentDate;
}
