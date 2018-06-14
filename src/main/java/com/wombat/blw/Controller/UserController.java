package com.wombat.blw.Controller;

import com.wombat.blw.Constant.CookieConstant;
import com.wombat.blw.Constant.RedisConstant;
import com.wombat.blw.DO.User;
import com.wombat.blw.Enum.ErrorCode;
import com.wombat.blw.Enum.RoleEnum;
import com.wombat.blw.Form.UserSignInForm;
import com.wombat.blw.Form.UserSignUpForm;
import com.wombat.blw.Service.UserService;
import com.wombat.blw.Util.CookieUtil;
import com.wombat.blw.Util.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/actions/signUp")
    public ModelAndView signUp(Map<String, Object> map, HttpServletResponse response,
                               @Validated UserSignUpForm userSignUpForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }
        User user = userService.create(userSignUpForm);
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_FORMAT, token),
                user.getUserId().toString(), RedisConstant.EXPIRE_TIME, TimeUnit.SECONDS);
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE_TIME);
        if (EnumUtil.getByCode(user.getRole(), RoleEnum.class) == RoleEnum.GENERAL) {
            //TODO Go to General user page

        } else if (EnumUtil.getByCode(user.getRole(), RoleEnum.class) == RoleEnum.ADMIN) {
            //TODO Go to Admin user page

        }
        return new ModelAndView("test");
    }

    @PostMapping("/actions/signIn")
    public ModelAndView signIn(Map<String, Object> map, HttpServletResponse response,
                               @Validated UserSignInForm userSignInForm) {
        User user = userService.getOne(userSignInForm);
        if (user == null) {
            map.put("msg", ErrorCode.USERNAME_NOT_EXIST.getMessage() + " 或 " + ErrorCode.INCORRECT_PASSWORD.getMessage());
            return new ModelAndView("common/error");
        }
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_FORMAT, token),
                user.getUserId().toString(), RedisConstant.EXPIRE_TIME, TimeUnit.SECONDS);
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE_TIME);
        if (EnumUtil.getByCode(user.getRole(), RoleEnum.class) == RoleEnum.GENERAL) {
            //TODO Go to General user page


        } else if (EnumUtil.getByCode(user.getRole(), RoleEnum.class) == RoleEnum.ADMIN) {
            //TODO Go to Admin user page

        }
        return new ModelAndView("test");
    }

    @RequestMapping("/actions/logout")
    public ModelAndView logout(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_FORMAT, cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ErrorCode.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/wombataudit");
        return new ModelAndView("common/success", map);
    }
}
