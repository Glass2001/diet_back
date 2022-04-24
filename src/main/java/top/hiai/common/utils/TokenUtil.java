package top.hiai.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import top.hiai.entity.User;

import java.util.Date;

/**
 * token工具类（生成、验证）
 */
public class TokenUtil {
    private static final long EXPIRE_TIME = 10 * 60 * 60 * 1000;//token到期时间10小时
    //    private static final long EXPIRE_TIME = 1;
    private static final String TOKEN_SECRET = "ljdyaishijin**3nkjnj??";  //密钥盐

    /**
     * @param : [user]
     * @return : java.lang.String
     * @throws :
     * @Description ：生成token
     * @author : lj
     * @date : 2020-1-31 22:49
     */
    public static String sign(User user) {

        String token = null;
        Date start = new Date();
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuedAt(start)        //签发时间
                    .withIssuer("auth0")//发行人
                    .withAudience(Integer.toString(user.getId()) )
                    .withClaim("username", user.getOpenid())//存放数据
                    .withExpiresAt(expireAt)//过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {
        }
        return token;
    }


    /**
     * @param : [token]
     * @return : java.lang.Boolean
     * @throws :
     * @Description ：token验证
     * @author : lj
     * @date : 2020-1-31 22:59
     */
    public static Boolean verify(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();//创建token验证器
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("过期时间：      " + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }
}
