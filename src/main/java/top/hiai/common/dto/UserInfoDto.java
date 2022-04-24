package top.hiai.common.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserInfoDto implements Serializable {

    /**
     * 使用总天数
     */
    private Integer useCount;

    /**
     * 备忘录总数
     */
    private Integer sumCount;


    private Integer addflag;
}
