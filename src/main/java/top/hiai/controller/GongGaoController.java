package top.hiai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.GetLoginUser;
import top.hiai.entity.Content;
import top.hiai.entity.GongGao;
import top.hiai.service.ContentService;
import top.hiai.service.GongGaoService;

import java.util.ArrayList;


@Controller
@RestController
@Api(tags = "APP端：公告管理")
@RequestMapping("/api/gonggao")
public class GongGaoController {

    @Autowired
    private GongGaoService gongGaoService ;

    @ApiOperation(value = "分页查询公告")
    @GetMapping("/list")
    public JsonResult getContentList(String s_name,Integer isAdmin,@RequestParam(defaultValue = "1") Integer page) {
        Integer userId = GetLoginUser.getLoginUser();
        return gongGaoService.getAllGongGaoList(s_name, page);
//        if(isAdmin == 1) {
//            return gongGaoService.getGongGaoList(s_name,userId,page);
//        }else {
//            //获取所有公告
//            return gongGaoService.getAllGongGaoList(s_name,page);
//        }
    }

    @ApiOperation(value = "根据id查找公告信息")
    @GetMapping("/findById/{id}")
    public GongGao findById(@PathVariable("id") Integer id){
        return this.gongGaoService.findById(id);
    }



    @ApiOperation(value = "保存/修改公告信息")
    @PostMapping("/save")
    public JsonResult saveGongGao(@RequestBody GongGao gongGao) {
        Integer userId = GetLoginUser.getLoginUser();
        return gongGaoService.saveGongGao(userId,gongGao);
    }

    @ApiOperation(value = "删除公告")
    @DeleteMapping("/del/{id}")
    public JsonResult deleteGongGao(@PathVariable(value = "id") Integer id) {
        return gongGaoService.deleteGongGao(id);
    }


}
