package com.wombat.blw.Aspect;

import com.wombat.blw.Constant.CookieConstant;
import com.wombat.blw.Constant.RedisConstant;
import com.wombat.blw.Exception.UserAuthorizeException;
import com.wombat.blw.Util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class UserAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.wombat.blw.Controller.*.*(..))" +
            "&& !execution(public * com.wombat.blw.Controller.OrganizationController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("[Login verify] Cannot find token in cookie");
            throw new UserAuthorizeException();
        }
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_FORMAT, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("[Login verify] Cannot find token in Redis");
            throw new UserAuthorizeException();
        }

    }
}
