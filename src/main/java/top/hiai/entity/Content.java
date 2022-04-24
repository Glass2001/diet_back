package top.hiai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

//import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("keep_content")
public class Content {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**用户ID**/
    @TableField("user_id")
    private Integer userId;

    //@NotBlank(message = "标题不能为空")
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 添加时间
     */
    @TableField("addtime")
    private Date addtime;

    /**
     * 是否加密
     * 0 不加密
     * 1 加密
     */
    @TableField("is_password")
    private Integer isPassword;

    /**
     * 加密的密码
     */
    @TableField("password")
    private String password;

    /**
     * 是否热门
     * 0 不热门
     * 1 热门
     */
    @TableField("is_hot")
    private Integer isHot;

    /**
     * 是否删除
     * 0 不删除
     * 1 删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 是否置顶
     * 0 不指定
     * 1 置顶
     */
    @TableField("is_top")
    private Integer isTop;
}
