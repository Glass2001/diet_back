package top.hiai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

//import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("keep_user")
public class User implements Serializable {

//    @NotBlank

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //@NotBlank(message = "Openid不能为空")
    @TableField("openid")
    private String openid;

    /**添加时间**/
    @TableField("addtime")
    private Date addtime;

    /**
     * 是否管理员
     * 0 不是管理
     * 1 是管理员
     */
    @TableField("is_admin")
    private Integer isAdmin;

    //@NotBlank(message = "密码不能为空")
    @TableField("password")
    private String password;

    @TableField("status")
    private Integer status;


    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("height")
    private String height;

    @TableField("weight")
    private String weight;


    private  String oldpassword;

    private  int addflag;

}
