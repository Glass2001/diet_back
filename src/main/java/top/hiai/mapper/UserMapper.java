package top.hiai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.hiai.common.dto.UserInfoDto;
import top.hiai.entity.User;


@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户登录授权
     *
     * @param openid
     * @return
     */
    @Select(" SELECT * FROM keep_user WHERE openid=#{openid} and password=#{password} ")
    User findByOpenidAndPassword(@Param("openid") String openid, @Param("password") String password);

    @Select(" SELECT * FROM keep_user WHERE openid=#{openid}")
    User findByOpenid(String openid);

    /**
     * 查询用户使用的天数和总的备忘录数量
     * @param userId
     * @return
     */
    @Select(" SELECT to_days( now( ) ) - to_days( addtime ) + 1 AS useCount, b.sumCount,a.addflag FROM keep_user a, ( SELECT count( * ) AS sumCount FROM keep_content WHERE user_id = #{userId} AND is_delete = 0 ) b WHERE a.id =#{userId} ")
    UserInfoDto getUserInfo(int userId);

    @Select("select id,name,age,height,weight from keep_user where id = #{userId}")
    User getUser(int userId);

    @Update("update keep_user set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") int status);
}
