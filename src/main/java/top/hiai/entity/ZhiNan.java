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
@TableName("keep_zhinan")
public class ZhiNan implements Serializable {

//    @NotBlank

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

   // @NotBlank(message = "食品名称不能为空")
    @TableField("fname")
    private String fname;

    @TableField("fvalue")
    private String fvalue;

    @TableField("fdanwei")
    private String fdanwei;

    @TableField("fflag")
    private  int fflag;

//    private  int uid;

}
