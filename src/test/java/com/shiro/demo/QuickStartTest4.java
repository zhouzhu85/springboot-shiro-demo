package com.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-28 14:49
 */
public class QuickStartTest4 {
    @Test
    public void testAuthertication(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:jdbcrealm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("jack","123");
        subject.login(token);
        System.out.println("是否拥有role1角色："+subject.hasRole("role1"));
        System.out.println("是否拥有video:find权限：" + subject.isPermitted("video:find"));
    }
}
