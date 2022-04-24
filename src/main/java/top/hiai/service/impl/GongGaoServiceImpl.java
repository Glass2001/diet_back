package top.hiai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.StringUtil;
import top.hiai.entity.Content;
import top.hiai.entity.GongGao;
import top.hiai.mapper.ContentMapper;
import top.hiai.mapper.GongGaoMapper;
import top.hiai.service.ContentService;
import top.hiai.service.GongGaoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class GongGaoServiceImpl implements GongGaoService {

    @Autowired
    private GongGaoMapper gongGaoMapper;

    @Override
    public JsonResult getGongGaoList(String s_name, Integer userId, Integer page) {
        //获取登录用户的id
//        User user = userMapper.findByOpenid(openid);
        QueryWrapper<GongGao> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .eq(GongGao::getWho, userId)
                .orderByDesc(GongGao::getDate);
        IPage<GongGao> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(gongGaoMapper.selectPage(contentIPage, queryWrapper));
    }

    /**
     * 保存公告
     * @param userId
     * @param gongGao
     * @return
     */
    @Override
    public JsonResult saveGongGao(Integer userId, GongGao gongGao) {
        if (StringUtil.isEmpty(gongGao.getTitle())) {
            return JsonResult.fail("标题不能为空");
        }
        if(StringUtil.isEmpty(gongGao.getContent())){
            return JsonResult.fail("内容不能为空");
        }
        if (gongGao.getId() == null) {
            //发布公告
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = simpleDateFormat.format(new Date());
            gongGao.setDate(date);
            gongGao.setWho(String.valueOf(userId));
            gongGaoMapper.insert(gongGao);
        }else {
            //修改
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = simpleDateFormat.format(new Date());
            gongGao.setDate(date);
            gongGao.setWho(String.valueOf(userId));
            gongGaoMapper.updateById(gongGao);

        }

        return JsonResult.succ(gongGao);
    }


    @Override
    public GongGao findById(Integer id) {
        return gongGaoMapper.selectById(id);
    }


    /**
     * 根据公告编号，删除公告
     * @param id
     * @return Json
     */
    @Override
    public JsonResult deleteGongGao(Integer id) {
        gongGaoMapper.deleteById(id);
        return JsonResult.succ();
    }

    @Override
    public JsonResult getAllGongGaoList(String s_name, Integer page) {
        QueryWrapper<GongGao> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .like(GongGao::getContent, s_name)
                .orderByDesc(GongGao::getDate);
        IPage<GongGao> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(gongGaoMapper.selectPage(contentIPage, queryWrapper));
    }
}
