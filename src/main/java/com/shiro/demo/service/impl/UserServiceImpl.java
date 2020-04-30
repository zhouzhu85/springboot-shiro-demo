package com.shiro.demo.service.impl;

import com.shiro.demo.dao.RoleMapper;
import com.shiro.demo.dao.UserMapper;
import com.shiro.demo.domain.Role;
import com.shiro.demo.domain.User;
import com.shiro.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-29 16:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findAllUserInfoByUsername(String username) {
        User user = userMapper.findByUsername(username);
        List<Role> roleList = roleMapper.findRoleListByUserId(user.getId());
        user.setRoleList(roleList);
        return user;
    }

    @Override
    public User findSimpleUserInfoById(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public User findSimpleUserInfoByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
