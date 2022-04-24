package top.hiai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.GetLoginUser;
import top.hiai.entity.Content;
import top.hiai.service.ContentService;
import top.hiai.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



@Controller
@RestController
@Api(tags = "APP端：备忘录管理")
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService ;

    @ApiOperation(value = "分页查询备忘录")
    @GetMapping("/list")
    public JsonResult getContentList(String s_name,Integer isAdmin,@RequestParam(defaultValue = "1") Integer page) {
        Integer userId = GetLoginUser.getLoginUser();
        return contentService.getAllContent(s_name, page);
//        if(isAdmin == 0) {
//            return contentService.getContentList(s_name,userId,page);
//        }else {
//            //系统管理员，查看所有的用户的个人备忘录信息
//            return contentService.getAllContent(s_name, page);
//        }
    }

    @ApiOperation(value = "查询回收站备忘录")
    @GetMapping("/list_del")
    public JsonResult getContentDeleteList() {
        Integer userId = GetLoginUser.getLoginUser();
        return contentService.getContentDeleteList(userId);
    }

    @ApiOperation(value = "保存/修改备忘录")
    @PostMapping("/save")
    public JsonResult saveContent(@RequestBody Content content) {
        Integer userId = GetLoginUser.getLoginUser();
        return contentService.saveContent(userId,content);
    }

    @ApiOperation(value = "根据ID查找备忘录")
    @GetMapping("/by/{id}")
    public JsonResult getById(@PathVariable(value = "id") int id) {
        return JsonResult.succ(contentService.getById(id));
    }

    @ApiOperation(value = "删除备忘录，并非真正删除")
    @DeleteMapping("/del/{id}")
    public JsonResult deleteUserContent(@PathVariable(value = "id") Integer id) {
        return contentService.deleteUserContent(id);
    }

    @ApiOperation(value = "永久删除备忘录")
    @DeleteMapping("/del_all")
    public JsonResult deleteUserContentAll(@RequestBody ArrayList<Integer> ids) {
        return contentService.deleteUserContentAll(ids);
    }
}
