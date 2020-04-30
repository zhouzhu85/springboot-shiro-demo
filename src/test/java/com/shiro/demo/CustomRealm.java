package com.shiro.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-28 17:02
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String,String> userinfo=new HashMap<>();
    {
        userinfo.put("jack","123");
        userinfo.put("xdclass","456");
    }

    Map<String,Set<String>> permissionMap=new HashMap<>();
    {
        Set<String> set1=new HashSet<>();
        Set<String> set2=new HashSet<>();
        set1.add("video:find");
        set2.add("video:buy");
        permissionMap.put("jack",set1);
        permissionMap.put("xdcalss",set2);
    }
    Map<String,Set<String>> roleMap=new HashMap<>();
    {
        Set<String> set1=new HashSet<>();
        Set<String> set2=new HashSet<>();
        set1.add("role1");
        set1.add("role2");
        set2.add("role");
        roleMap.put("jack",set1);
        roleMap.put("xdcalss",set2);
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String name=(String)principals.getPrimaryPrincipal();
        Set<String> pemissions=getPermissionByName(name);
        Set<String> roles=getRolesByName(name);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(pemissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getRolesByName(String name) {
        return roleMap.get(name);
    }

    private Set<String> getPermissionByName(String name) {
        return permissionMap.get(name);
    }

    //登录信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token获取身份信息，token代表用户输入的信息
        String name = (String) token.getPrincipal();
        //模拟从数据库中取密码
        String pwd = getPwdByUserNameFromDB(name);
        if (pwd==null || "".equals(pwd)){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(name,pwd,this.getName());
        return authenticationInfo;
    }

    private String getPwdByUserNameFromDB(String name){
        return userinfo.get(name);
    }
}
