package top.hiai.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.hiai.entity.User;
import top.hiai.mapper.UserMapper;
import top.hiai.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**

 * 获取登录用户信息
 */
public class GetLoginUser {

    private static final String TOKEN_SECRET = "ljdyaishijin**3nkjnj??";  //密钥盐

    public static Integer getLoginUser() {
        String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();//创建token验证器
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String userId = JWT.decode(token).getAudience().get(0);
//        System.out.println("userId = " + userId);
        return Integer.parseInt(userId);
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
