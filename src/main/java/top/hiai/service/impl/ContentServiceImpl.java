package top.hiai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hiai.common.result.JsonResult;
import top.hiai.common.utils.StringUtil;
import top.hiai.entity.Content;
import top.hiai.entity.User;
import top.hiai.mapper.ContentMapper;
import top.hiai.mapper.UserMapper;
import top.hiai.service.ContentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;



@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public JsonResult getContentList(String s_name, Integer userId, Integer page) {
        //获取登录用户的id
//        User user = userMapper.findByOpenid(openid);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .eq(Content::getIsDelete, 0)
                .eq(Content::getUserId, userId)
                .like(Content::getContent, s_name)
                .orderByDesc(Content::getIsTop, Content::getAddtime);
        IPage<Content> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(contentMapper.selectPage(contentIPage, queryWrapper));
    }

    @Override
    public JsonResult getAllContent(String s_name, Integer page) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .eq(Content::getIsDelete, 0)
                .like(Content::getContent, s_name)
                .orderByDesc(Content::getIsTop, Content::getAddtime);
        IPage<Content> contentIPage = new Page<>(page, 100);//参数一是当前页，参数二是每页个数
        return JsonResult.succ(contentMapper.selectPage(contentIPage, queryWrapper));
    }

    @Override
    public JsonResult getContentDeleteList(Integer userId) {
        //获取登录用户的id
//        User user = userMapper.findByOpenid(openid);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();//查询条件生成器
        queryWrapper.lambda()
                .eq(Content::getIsDelete, 1)
                .eq(Content::getUserId, userId);
        List<Content> contents = contentMapper.selectList(queryWrapper);
        return JsonResult.succ(contents);
    }

    @Override
    public JsonResult saveContent(Integer userId, Content content) {
        if (StringUtil.isEmpty(content.getTitle())) {
            return JsonResult.fail("标题不能为空");
        }
//        User user = userMapper.findByOpenid(openid);
        if (content.getId() == null) {
            content.setAddtime(new Date());
            content.setUserId(userId);
            contentMapper.insert(content);
        } else {
            content.setUserId(userId);
            content.setAddtime(new Date());
            contentMapper.updateById(content);
        }
        return JsonResult.succ(content);
    }

    @Override
    public Content getById(int id) {
        return contentMapper.selectById(id);
    }

    @Override
    public JsonResult deleteUserContent(Integer id) {
        Content content = contentMapper.selectById(id);
        content.setIsDelete(1);
        contentMapper.updateById(content);
        return JsonResult.succ();
    }

    @Override
    public JsonResult deleteUserContentAll(ArrayList<Integer> ids) {
        for (Integer id : ids) {
            contentMapper.deleteById(id);
        }
        return JsonResult.succ();
    }
}
