package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Company;
import com.wombat.blw.DO.Participate;
import com.wombat.blw.DO.Project;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.SimpleUserDTO;
import com.wombat.blw.Enum.CompanyRoleEnum;
import com.wombat.blw.Enum.OrgRoleEnum;
import com.wombat.blw.Form.UserSignInForm;
import com.wombat.blw.Form.UserSignUpForm;
import com.wombat.blw.Mapper.*;
import com.wombat.blw.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private WebSocket webSocket;
//
//    @Override
//    public void createReceipt(User user) {
//        userMapper.insert(user);
//
//        //send WebSocket message
//        webSocket.sendMessage("New User!");
//    }
//
//    @Override
//    public void delete(Integer userId) {
//
//    }
//
//    @Override
//    public List<User> getAll() {
//        return userMapper.getAll();
//    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ParticipateMapper participateMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Override
    public User getOne(UserSignInForm userSignInForm) {
        User user = userMapper.findUserByUsername(userSignInForm.getUsername());
        if (user == null) {
            //TODO Username does not exist
            return null;
        }
        if (!user.getPassword().equals(userSignInForm.getPassword())) {
            //TODO Incorrect password
            return null;
        } else {
            return user;
        }
    }

    @Override
    public User create(UserSignUpForm userSignUpForm) {
        if (userMapper.findUserByUsername(userSignUpForm.getUsername()) != null) {
            //TODO Username already exists

        }
        User user = new User();
        BeanUtils.copyProperties(userSignUpForm, user);
        userMapper.create(user);
        return userMapper.findUserByUserId(user.getUserId());
    }

    @Override
    public SimpleUserDTO findSimpleOne(Integer userId) {
        User user = userMapper.findUserByUserId(userId);
        Company company = companyMapper.selectByCompanyId(user.getCompanyId());
        return new SimpleUserDTO(user.getUsername(), company.getName(), company.getCompanyId());
    }

    @Override
    public Boolean ifManagesOrg(Integer userId, Integer orgId) {
        Participate participate = participateMapper.findOne(userId, orgId);
        return participate.getRole().equals(OrgRoleEnum.MANAGER.getCode());
    }

    @Override
    public Boolean ifManagesPrj(Integer userId, Integer prjId) {
        Project project = projectMapper.findById(prjId);
        return ifManagesOrg(userId, project.getOrgId());
    }

    @Override
    public User findReceiptSubmitter(Integer rcptId) {
        Integer userId = receiptMapper.findUserIdByRcptId(rcptId);
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public List<Integer> findAdminIdList(Integer coId) {
        List<User> userList = userMapper.findUserListInCompanyOfRole(coId, CompanyRoleEnum.ADMIN.getCode());
        return userList.stream().map(e -> e.getUserId()).collect(Collectors.toList());
    }

    @Override
    public String findRealName(Integer userId) {
        return userMapper.findRealNameByUserId(userId);
    }
}
