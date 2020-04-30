package com.shiro.demo;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-27 17:14
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试用例
 */
public class QuickStartTest2 {
    private SimpleAccountRealm accountRealm=new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();

    @Before
    public void init(){
        //初始化数据源
        accountRealm.addAccount("xdclass","123");
        accountRealm.addAccount("jack","456");

        //构建环境
        defaultSecurityManager.setRealm(accountRealm);
    }

    @Test
    public void testAuthentication(){
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //主体
        Subject subject = SecurityUtils.getSubject();

        //用户输入的账号和密码
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("jack","456");
        subject.login(usernamePasswordToken);
        System.out.println("用户信息："+subject.getPrincipal());
        System.out.println("是否有root角色："+subject.hasRole("root"));
        subject.logout();
        System.out.println("认证是否成功："+subject.isAuthenticated());
    }
}
