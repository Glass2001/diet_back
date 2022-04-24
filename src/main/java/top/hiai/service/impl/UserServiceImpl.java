package top.hiai.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hiai.common.dto.UserInfoDto;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.StringUtil;
import top.hiai.common.utils.TokenUtil;
import top.hiai.entity.Content;
import top.hiai.entity.User;
import top.hiai.entity.YinShi;
import top.hiai.entity.ZhiNan;
import top.hiai.mapper.UserMapper;
import top.hiai.mapper.YinShiMapper;
import top.hiai.mapper.ZhiNanMapper;
import top.hiai.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private  YinShiMapper yinShiMapper;

    @Autowired
    private ZhiNanMapper zhiNanMapper;

    @Override
    public JsonResult getYinShiJLList(String s_name, Integer page, Integer id) {
        QueryWrapper<YinShi> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .eq(YinShi::getUid, id);
        IPage<YinShi> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(yinShiMapper.selectPage(contentIPage, queryWrapper));

    }

    @Override
    public JsonResult deleteYinShiJL(Integer id) {
        yinShiMapper.deleteById(id);
        return JsonResult.succ();
    }

    @Override
    public ZhiNan findById(Integer id) {
        return this.zhiNanMapper.selectById(id);
    }


    /**
     * 用户个人饮食添加
     * @param yinShi
     * @return
     */
    @Override
    public JsonResult addMyYinShiInfo(YinShi yinShi) {
        yinShi.setAddtime(new Date());
        yinShiMapper.insert(yinShi);
        return JsonResult.succ(yinShi);
    }

    /**
     * 认证当前用户信息 是否存在
     * @param openid
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public JsonResult userAuth(String openid,String password) throws Exception {
        User user = null;
        user = userMapper.findByOpenidAndPassword(openid,password);
        //用户不存在的情况
//        if (null == user) {
//            //用户不存在添加一条数据
//            user = User.builder()
//                    .openid(openid)
//                    .addtime(new Date())
//                    .build();
//            userMapper.insert(user);
//        }
//        user = userMapper.findByOpenid(openid);

        if(user!=null) {
            //用户存在的情况下
            String token = TokenUtil.sign(user);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValueAsString(token);

            return JsonResult.succ(MapUtil.builder()
                    .put("userInfo", user)
                    .put("token", token)
                    .map());
        }else  {
            throw new Exception();
        }

    }

    @Override
    public User getUserByOpenid(String openid) {
        return userMapper.findByOpenid(openid);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);

    }

    @Override
    public UserInfoDto getUserInfo(int userId) {
        return userMapper.getUserInfo(userId);
    }

    @Override
    public User loadMyInfo(int userId) {
        return userMapper.getUser(userId);
    }


    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public JsonResult register(User user) {
        if (StringUtil.isEmpty(user.getOpenid())) {
            return JsonResult.fail("openidU不能为空");
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            return JsonResult.fail("密码不能为空");
        }
        user.setAddtime(new Date());
        user.setIsAdmin(0);
        userMapper.insert(user);
        return JsonResult.succ(user);
    }

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    @Override
    public JsonResult updatepwd(User user) {
        User u = userMapper.findByOpenidAndPassword(user.getOpenid(),user.getOldpassword());
        if(u!=null){
            u.setPassword(user.getPassword());
            userMapper.updateById(u);

            return JsonResult.succ();

        }else{
            return  JsonResult.fail("密码修改失败!");
        }

    }

    /**
     * 添加个人信息
     * @param user
     * @return
     */
    public JsonResult addMyInfo(User user) {
        User user1 = userMapper.selectById(user.getId());
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        user1.setHeight(user.getHeight());
        user1.setWeight(user.getWeight());
        user1.setAddflag(1);
        int num = userMapper.updateById(user1);

        if (num > 0) {
            return  JsonResult.succ();
        }else{
            return JsonResult.fail("个人信息添加失败");
        }

    }



    @Override
    public JsonResult login(User user) throws Exception {
        return userAuth(user.getOpenid(),user.getPassword());
    }

    /**
     * 查询系统用户
     * @param s_name
     * @param page
     * @return
     */
    @Override
    public JsonResult getUserList(String s_name, Integer page) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .like(User::getOpenid, s_name)
                .orderByDesc(User::getAddtime);
        IPage<User> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(userMapper.selectPage(contentIPage, queryWrapper));
    }

    /**
     * 修改用户状态
     *
     * @param id
     * @param flag
     * @return
     */
    @Override
    public JsonResult updateStatus(Integer id, Integer flag) {
        //flag==1 表示启用当前账户  flag==0 表示禁用当前账户
        int status = 0;
        if (flag == 1) {
            status = 1;
        }
        User user = new User();
        user.setStatus(status);

        int affectedRow = userMapper.updateStatus(id,status);
        return JsonResult.succ(affectedRow);
    }

    @Override
    public JsonResult addZhiNanInfo(ZhiNan zhiNan) {
        if(StringUtil.isEmpty(zhiNan.getFname())){
            return JsonResult.fail("名称不能为空");
        }
        if(StringUtil.isEmpty(zhiNan.getFvalue())){
            return JsonResult.fail("数量不能为空");
        }
        if(StringUtil.isEmpty(zhiNan.getFdanwei())){
            return JsonResult.fail("单位不能为空");
        }
        if(zhiNan.getId()==null){
            zhiNanMapper.insert(zhiNan);
        }else {
            zhiNanMapper.updateById(zhiNan);
        }

        return  JsonResult.succ(zhiNan);
    }

    @Override
    public JsonResult getZhiNanList(String s_name, Integer userId, Integer page) {
        QueryWrapper<ZhiNan> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .like(ZhiNan::getFname,s_name);

        IPage<ZhiNan> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(zhiNanMapper.selectPage(contentIPage, queryWrapper));
    }

    @Override
    public JsonResult getAllZhiNanList(String s_name, Integer page) {
        QueryWrapper<ZhiNan> queryWrapper = new QueryWrapper<>();//查询条件生成器

        IPage<ZhiNan> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(zhiNanMapper.selectPage(contentIPage, queryWrapper));
    }

    @Override
    public JsonResult deleteZhiNan(Integer id) {
        zhiNanMapper.deleteById(id);
        return JsonResult.succ();
    }

}
