package com.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-28 14:49
 */
public class QuickStartTest5 {
    CustomRealm customRealm=new CustomRealm();
    DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();

    @Before
    public void init(){
        //构建环境
        defaultSecurityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    @Test
    public void testAuthertication(){
        //获取当前的操作的主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("jack","123");

        subject.login(token);
        System.out.println("认证结果：" + subject.isAuthenticated());

        System.out.println("登录信息：" + subject.getPrincipals());

        System.out.println("是否拥有role1角色："+subject.hasRole("role1"));
        System.out.println("是否拥有video:find权限：" + subject.isPermitted("video:find"));
    }
}
