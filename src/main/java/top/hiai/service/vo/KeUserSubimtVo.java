package top.hiai.service.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author hkq
 * @Email goodsking@163.com
 */
@Data
public class KeUserSubimtVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String openid;

    private String password;
}
