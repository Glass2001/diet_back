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
@TableName("keep_gonggao")
public class GongGao {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    //@NotBlank(message = "公告主题不能为空")
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
    @TableField("date")
    private String date;

    /**
     * 发布者
     */
    @TableField("who")
    private String who;


}
