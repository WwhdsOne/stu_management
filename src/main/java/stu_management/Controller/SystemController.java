package stu_management.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu_management.Service.DictService;
import stu_management.Service.Impl.DictServiceImpl;
import stu_management.entity.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wwh
 * @ProjectName stu_management
 * @dateTime 2024/5/2 下午4:28
 * @description 系统
 **/
@RestController
@RequestMapping("/system")
@Slf4j
public class SystemController {


    private final DictService dictService;
    @Autowired
    public SystemController(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 获取字典
     * @return Result
     */
    @PostMapping("/dict")
    public Result getDictJson() {
        return dictService.getDictJson();
    }

    /**
     * 获取用户信息
     * @param request
     * @return Result
     */
    @PostMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        return dictService.getUserInfo(request);
    }
}
