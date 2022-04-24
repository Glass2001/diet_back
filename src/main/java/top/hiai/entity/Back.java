package top.hiai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("keep_back")
public class Back implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 反馈内容
     */
    @TableField("content")
    private String content;

    /**
     * 是否解决
     * 0 没有解决
     * 1 解决
     */
    @TableField("is_solve")
    private Integer isSolve;

    /**
     * 添加时间
     */
    @TableField("addtime")
    private Date addtime;

    /**
     * 联系方式
     */
    @TableField("contact")
    private String contact;

    /**
     * 管理员回复
     */
    @TableField("reply")
    private String reply;


}
