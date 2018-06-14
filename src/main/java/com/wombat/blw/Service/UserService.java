package com.wombat.blw.Service;

import com.wombat.blw.DO.User;
import com.wombat.blw.Form.UserSignInForm;
import com.wombat.blw.Form.UserSignUpForm;

public interface UserService {

    /**
     * 根据用户登录提交的表单信息查找对应的用户信息
     *
     * @param userSignInForm
     * @return
     */
    User getOne(UserSignInForm userSignInForm);

    /**
     * 根据注册表单创建用户
     *
     * @param userSignUpForm
     * @return 返回创建的用户对象
     */
    User create(UserSignUpForm userSignUpForm);
}
