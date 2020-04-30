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
 * @create 2020-04-28 9:51
 */
public class QuickStartTest3 {
    @Test
    public void testAuthertication(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("jack","456");
        subject.login(token);
        System.out.println("用户信息："+subject.getPrincipal());
        System.out.println("是否有user角色："+subject.hasRole("user"));
        System.out.println("是否有user角色权限："+subject.isPermitted("video:find"));

    }
}
