package stu_management.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import stu_management.DAO.DictMapper;
import stu_management.Service.Impl.DictServiceImpl;
import stu_management.entity.Dict;
import stu_management.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/2 下午4:37
 * @description DictService
 **/

public interface DictService extends IService<Dict> {
    Result getDictJson();

    Result getUserInfo(HttpServletRequest request);
}
