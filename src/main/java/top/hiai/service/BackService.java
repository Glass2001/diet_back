package top.hiai.service;

import io.swagger.models.auth.In;
import top.hiai.common.result.JsonResult;
import top.hiai.entity.Back;


public interface BackService {

    /**
     * 用户提交反馈信息
     * @param userId
     * @param back
     * @return
     */
    JsonResult addBack(Integer userId, Back back);

    /**
     * 用户查看自己的反馈信息
     * @param userId
     * @return
     */
    JsonResult getUserBack(Integer userId,String s_name,Integer page);

    /**
     * 删除反馈信息
     * @param id
     * @return
     */
    JsonResult deleteUserBack(Integer id);

}
