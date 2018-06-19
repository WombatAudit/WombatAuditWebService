package com.wombat.blw.Service;

import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.SimpleUserDTO;
import com.wombat.blw.Form.UserSignInForm;
import com.wombat.blw.Form.UserSignUpForm;

public interface UserService {

    User getOne(UserSignInForm userSignInForm);

    User create(UserSignUpForm userSignUpForm);

    SimpleUserDTO findSimpleOne(Integer userId);

    Boolean ifManagesOrg(Integer userId, Integer orgId);

    Boolean ifManagesPrj(Integer userId, Integer prjId);

    User findReceiptSubmitter(Integer rcptId);
}
