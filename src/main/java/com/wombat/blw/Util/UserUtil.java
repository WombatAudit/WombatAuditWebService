package com.wombat.blw.Util;

import com.wombat.blw.Constant.CookieConstant;
import com.wombat.blw.Constant.RedisConstant;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    /**
     * Get userId from cookie and redis
     *
     * @param request
     * @param redisTemplate
     * @return
     */
    public static Integer getUserId(HttpServletRequest request, StringRedisTemplate redisTemplate) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            return null;
        }
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_FORMAT, cookie.getValue()));
        Integer result;
        try {
            result = Integer.valueOf(tokenValue);
        } catch (NumberFormatException e) {
            return null;
        }
        return result;
    }

}
