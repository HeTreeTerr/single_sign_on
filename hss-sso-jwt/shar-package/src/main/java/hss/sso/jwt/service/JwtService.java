package hss.sso.jwt.service;

import com.auth0.jwt.interfaces.Claim;

import hss.sso.jwt.entity.User;
import hss.sso.jwt.exception.CustomException;
import hss.sso.jwt.util.JwtUtil;
import hss.sso.jwt.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {

    private Logger logger = LoggerFactory.getLogger(JwtService.class);

    /**
     * jwt token超时时间，单位ms
     */
    private static int expireTime;

    @Value("${jwt_expire_time}")
    public void setExpireTime(int expireTime) {
        JwtService.expireTime = expireTime * 60 * 1000;
    }

    /**
     * Description:登录获取token
     *
     * @param user user
     * @return java.lang.String
     * @author fanxb
     * @date 2019/3/4 18:45
     */
    public String login(User user) {
        //进行登录校验
        try {
            if (user.getName().equalsIgnoreCase(user.getPassword())) {
                return this.generateNewJwt(user.getName());
            } else {
                logger.info("账号密码错误:{}{}", user.getName(), user.getPassword());
                throw new CustomException("账号密码错误");
            }
        } catch (Exception e) {
            logger.info("账号密码错误:{},{}", user.getName(), user.getPassword());
            throw new CustomException(e, "账号密码错误");
        }
    }

    /**
     * 过期时间小于半小时，返回新的jwt，否则返回原jwt
     * @param jwt
     * @return
     */
    public String refreshJwt(String jwt){
        String secret = RedisUtil.redisTemplate.opsForValue().get(jwt);
        Map<String, Claim> map = JwtUtil.decode(jwt,secret);
        //如果令牌的有效期小于半小时，重新生成新令牌
        if(map.get("exp").asLong()*1000 - System.currentTimeMillis()<30*60*1000){
            return this.generateNewJwt(map.get("name").asString());
        }else{
            return jwt;
        }
    }


    /**
     * Description: 生成新的jwt,并放入jwtMap中
     *
     * @return java.lang.String
     * @author fanxb
     * date 2019/3/5 10:44
     */
    private String generateNewJwt(String name) {
        String secret = UUID.randomUUID().toString().replaceAll("-", "");
        String token = JwtUtil.encode(name, secret, expireTime);
        RedisUtil.set(token, secret, expireTime);
        return token;
    }

    /**
     * Description:检查jwt有效性
     *
     * @return Boolean
     * @author fanxb
     * @date 2019/3/4 18:47
     */
    public Boolean checkJwt(String jwt) {
        try {
            String secret = RedisUtil.redisTemplate.opsForValue().get(jwt);
            JwtUtil.decode(jwt, secret);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Description: 使该jwt失效
     *
     * @author fanxb
     * @date 2019/3/4 19:58
     */
    public void inValid(String jwt) {
        RedisUtil.delete(jwt);
    }

}
