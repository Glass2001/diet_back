package top.hiai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hiai.common.result.JsonResult;
import top.hiai.entity.Content;
import top.hiai.entity.GongGao;

import java.util.ArrayList;


public interface GongGaoService {

    /**
     * 查询登录用户备忘录内容
     * @param s_name
     * @return
     */
    JsonResult getGongGaoList(String s_name, Integer userId, Integer page);

    /**
     * 保存或者修改备忘录
     * @param userId
     * @return
     */
    JsonResult saveGongGao(Integer userId, GongGao gongGao);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    GongGao findById(Integer id);


    JsonResult deleteGongGao(Integer id);


    JsonResult getAllGongGaoList(String s_name, Integer page);
}
