package com.shiro.demo.service;

import com.shiro.demo.domain.User;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-29 16:17
 */
public interface UserService {
    /**
     * 获取全部用户信息，包括角色、权限
     * @param username
     * @return
     */
    User findAllUserInfoByUsername(String username);

    /**
     * 获取用户基本信息
     * @param userId
     * @return
     */
    User findSimpleUserInfoById(Integer userId);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    User findSimpleUserInfoByUsername(String username);
}
