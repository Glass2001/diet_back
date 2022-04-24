package top.hiai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.GetLoginUser;
import top.hiai.entity.User;
import top.hiai.entity.YinShi;
import top.hiai.entity.ZhiNan;
import top.hiai.service.UserService;
import top.hiai.service.vo.KeUserSubimtVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RestController
@Api(tags = "APP端：用户管理")
@RequestMapping("/api/user")
public class UserController {

    @ApiOperation(value = "分页查询膳食指南")
    @GetMapping("/zhinanlist")
    public JsonResult getContentList(String s_name,Integer isAdmin,@RequestParam(defaultValue = "1") Integer page) {
        Integer userId = GetLoginUser.getLoginUser();
        if(isAdmin == 1) {
            return userService.getZhiNanList(s_name,userId,page);
        }else {
            //获取所有膳食指南
            return userService.getAllZhiNanList(s_name,page);
        }
    }


    @ApiOperation(value = "个人饮食记录查询")
    @GetMapping("/jilulist")
    public JsonResult jilulist(String s_name,Integer isAdmin,@RequestParam(defaultValue = "1") Integer page) {
        Integer userId = GetLoginUser.getLoginUser();
        return userService.getYinShiJLList(s_name,page,userId);
    }

    @ApiOperation(value = "删除膳食模板指南内容")
    @DeleteMapping("/yinshijldel/{id}")
    public JsonResult yinshijldel(@PathVariable(value = "id") Integer id) {
        return userService.deleteYinShiJL(id);
    }

    @ApiOperation(value = "删除膳食模板指南内容")
    @DeleteMapping("/zhinandel/{id}")
    public JsonResult deleteGongGao(@PathVariable(value = "id") Integer id) {
        return userService.deleteZhiNan(id);
    }

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录授权")
    @PostMapping("/auth")
    public JsonResult getHotCourse(@RequestBody KeUserSubimtVo userSubimtVo)throws Exception {
        return userService.userAuth(userSubimtVo.getOpenid(),userSubimtVo.getPassword());
    }

    @ApiOperation(value = "登录用户信息")
    @GetMapping("/get_login")
    public JsonResult getLogin(){
        Integer userId = GetLoginUser.getLoginUser();
        return JsonResult.succ( userService.getUserById(userId));
    }

    @ApiOperation(value = "获取用户使用天数")
    @GetMapping("/get_info")
    public JsonResult getUserInfo(){
        Integer userId = GetLoginUser.getLoginUser();
        return JsonResult.succ( userService.getUserInfo(userId));
    }


    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public JsonResult register(@RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation(value = "饮食休息添加")
    @PostMapping("/addMyYinShiInfo")
    public JsonResult addMyYinShiInfo(@RequestBody YinShi yinShi) {
        return userService.addMyYinShiInfo(yinShi);
    }




    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public JsonResult login(@RequestBody User user) throws Exception {
        return userService.login(user);
    }


    @ApiOperation(value = "修改密码")
    @PostMapping("/updatepwd")
    public JsonResult updatepwd(@RequestBody User user) throws Exception {
        return userService.updatepwd(user);
    }

    @ApiOperation(value = "添加个人信息")
    @PostMapping("/addMyInfo")
    public JsonResult addMyInfo(@RequestBody User user){

        return  userService.addMyInfo(user);

    }

    @ApiOperation(value = "查询个人信息")
    @PostMapping("/loadMyInfo")
    public JsonResult loadMyInfo(@RequestBody User user){

        return  JsonResult.succ(userService.loadMyInfo(user.getId()));

    }

    /**
     * 查询系统用户
     * @param s_name
     * @param page
     * @return
     */
    @ApiOperation(value = "查询系统用户")
    @GetMapping("/list")
    public JsonResult getContentList(String s_name,@RequestParam(defaultValue = "1") Integer page) {
        Integer userId = GetLoginUser.getLoginUser();
        return userService.getUserList(s_name,page);
    }

    @ApiOperation(value = "修改用户账户状态")
    @GetMapping("/updateStatus")
    public JsonResult updateStatus(@Param("id") Integer id,@Param("flag") Integer flag) {

        return userService.updateStatus(id, flag);

    }

    @ApiOperation(value = "指南信息保存与修改")
    @PostMapping("/zhinansave")
    public JsonResult zhinansave(@RequestBody ZhiNan zhiNan){

        return  userService.addZhiNanInfo(zhiNan);

    }

    @ApiOperation(value = "查找指南信息")
    @GetMapping("/findById/{id}")
    public ZhiNan findById(@PathVariable("id") Integer id){
        return  this.userService.findById(id);
    }



}
