package top.hiai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.hiai.entity.Content;
import top.hiai.entity.User;


@Repository
public interface ContentMapper extends BaseMapper<Content> {
}
