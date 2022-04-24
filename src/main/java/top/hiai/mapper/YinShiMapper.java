package top.hiai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.hiai.common.dto.UserInfoDto;
import top.hiai.entity.User;
import top.hiai.entity.YinShi;


@Repository
public interface YinShiMapper extends BaseMapper<YinShi> {


}
