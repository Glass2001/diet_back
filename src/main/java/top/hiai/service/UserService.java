package top.hiai.service;

import top.hiai.common.dto.UserInfoDto;
import top.hiai.common.result.JsonResult;
import top.hiai.entity.GongGao;
import top.hiai.entity.User;
import top.hiai.entity.YinShi;
import top.hiai.entity.ZhiNan;


public interface UserService {


    JsonResult addMyYinShiInfo(YinShi yinShi);


    /**
     * 用户登录授权
     * @param openid
     * @return
     */
    JsonResult userAuth(String openid,String password)throws Exception;

    /**
     * 根据用户openid获取用户信息
     * @param openid
     * @return
     */
    User getUserByOpenid(String openid);

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 查询用户使用的天数和总的备忘录数量
     * @param userId
     * @return
     */
    UserInfoDto getUserInfo(int userId);

    User loadMyInfo(int userId);

    JsonResult register(User user);

    JsonResult updatepwd(User user);

    JsonResult addMyInfo(User user);

    JsonResult login(User user) throws Exception;

    JsonResult getUserList(String s_name, Integer page);

    JsonResult updateStatus(Integer id, Integer flag);

    JsonResult addZhiNanInfo(ZhiNan zhiNan);

    JsonResult getZhiNanList(String s_name, Integer userId, Integer page);
    JsonResult getAllZhiNanList(String s_name, Integer page);

    JsonResult deleteZhiNan(Integer id);

    JsonResult getYinShiJLList(String s_name, Integer page,Integer id);

    JsonResult deleteYinShiJL(Integer id);

    /**
     * 根据ID查找指南信息
     * @param id
     * @return
     */
    ZhiNan findById(Integer id);
}
