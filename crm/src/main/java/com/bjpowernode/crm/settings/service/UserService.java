package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();


}
