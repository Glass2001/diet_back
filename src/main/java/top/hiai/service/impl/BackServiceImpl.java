package top.hiai.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.StringUtil;
import top.hiai.entity.Back;
import top.hiai.mapper.BackMapper;
import top.hiai.service.BackService;

import java.util.Date;


@Service
public class BackServiceImpl implements BackService {

    @Autowired
    private BackMapper backMapper;

    @Override
    public JsonResult addBack(Integer userId, Back back) {
        if(StringUtil.isEmpty(back.getContent())){
            return JsonResult.fail("反馈内容不能为空");
        }
        if(StringUtil.isEmpty(back.getContact())){
            return JsonResult.fail("联系方式不能为空");
        }
        back.setAddtime(new Date());
        back.setUserId(userId);
        return JsonResult.succ(backMapper.insert(back)) ;
    }

    @Override
    public JsonResult getUserBack(Integer userId,String s_name,Integer page) {
        QueryWrapper<Back> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
//                .eq(Back::getUserId, userId)
                .like(Back::getContent, s_name)
                .orderByDesc(Back::getAddtime);
        IPage<Back> contentIPage = new Page<>(page, 10);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(backMapper.selectPage(contentIPage, queryWrapper));
    }

    @Override
    public JsonResult deleteUserBack(Integer id) {
        int result = backMapper.deleteById(id);
        if(result >0){
            return JsonResult.succ();
        }else{
            return JsonResult.fail("内容不存在");
        }
    }
}
