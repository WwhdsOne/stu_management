package stu_management.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu_management.entity.Dict;
import stu_management.entity.UserDTO;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/2 下午4:38
 * @description DictMapper
 **/
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
}
