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
@TableName("keep_yinshi")
public class YinShi implements Serializable {

//    @NotBlank

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("wucan")
    private String wucan;

    /**添加时间**/
    @TableField("addtime")
    private Date addtime;


    @TableField("zaocan")
    private String zaocan;

    @TableField("wancan")
    private String wancan;

    @TableField("uid")
    private Integer uid;


}
