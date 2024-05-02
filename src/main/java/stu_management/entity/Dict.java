package stu_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/2 下午4:30
 * @description 字典
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("dict")
public class Dict implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;

    @TableField("type")
    private String type;

    @TableField("code")
    private Integer code;

    @TableField("value")
    private String value;
}
