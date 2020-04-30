package com.shiro.demo.config;

import com.shiro.demo.domain.Permission;
import com.shiro.demo.domain.Role;
import com.shiro.demo.domain.User;
import com.shiro.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-29 14:50
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 进行权限校验
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("进行授权");
        String username =(String) principals.getPrimaryPrincipal();
        User user = userService.findAllUserInfoByUsername(username);
        List<String> stringRoleList=new ArrayList<>();
        List<String> stringPermissionList=new ArrayList<>();
        List<Role> roleList = user.getRoleList();
        for (Role role:roleList){
            stringRoleList.add(role.getName());
            List<Permission> permissionList = role.getPermissionList();
            for (Permission permission:permissionList){
                if (permission!=null) {
                    stringPermissionList.add(permission.getName());
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户登录的时候会调用
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("登录认证");
        String username =(String) token.getPrincipal();
        User user = userService.findAllUserInfoByUsername(username);
        String pwd = user.getPassword();
        if (pwd==null || "".equals(pwd)){
            return null;
        }
        return new SimpleAuthenticationInfo(user.getUsername(),pwd,this.getClass().getName());
    }
}
