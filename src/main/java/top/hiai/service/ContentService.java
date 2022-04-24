package top.hiai.service;

import top.hiai.common.result.JsonResult;
import top.hiai.entity.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public interface ContentService {

    /**
     * 查询登录用户备忘录内容
     * @param s_name
     * @return
     */
    JsonResult getContentList(String s_name,Integer userId,Integer page);

    JsonResult getAllContent(String s_name,Integer page);

    /**
     * 查询用户回收站的内容
     * @param userId
     * @return
     */
    JsonResult getContentDeleteList(Integer userId);

    /**
     * 保存或者修改备忘录
     * @param userId
     * @return
     */
    JsonResult saveContent(Integer userId, Content content);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    Content getById(int id);

    /**
     * 把备忘录删除到回收站中
     * @param id
     * @return
     */
    JsonResult deleteUserContent(Integer id);

    /**
     * 永久删除备忘录
     * @param ids
     * @return
     */
    JsonResult deleteUserContentAll(ArrayList<Integer> ids);

}
