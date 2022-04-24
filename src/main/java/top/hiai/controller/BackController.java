package top.hiai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.GetLoginUser;
import top.hiai.entity.Back;
import top.hiai.entity.Content;
import top.hiai.service.BackService;
import top.hiai.service.ContentService;


@Controller
@RestController
@Api(tags = "APP端：用户反馈管理")
@RequestMapping("/api/back")
public class BackController {

    @Autowired
    private BackService backService;

    @ApiOperation(value = "提交反馈信息")
    @PostMapping("/save")
    public JsonResult saveContent(@RequestBody Back back) {
        Integer userId = GetLoginUser.getLoginUser();
        return backService.addBack(userId,back);
    }

    @ApiOperation(value = "显示个人反馈信息")
    @GetMapping("/my")
    public JsonResult getUserBack(String s_name,@RequestParam(defaultValue = "1") Integer page) {
        Integer userId = GetLoginUser.getLoginUser();
        return backService.getUserBack(userId,s_name,page);
    }

    @ApiOperation(value = "删除反馈信息")
    @DeleteMapping("/del/{id}")
    public JsonResult deleteUserBack(@PathVariable(value = "id") Integer id) {
        return backService.deleteUserBack(id);
    }
}
