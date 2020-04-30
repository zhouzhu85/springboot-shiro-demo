package com.shiro.demo.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouzhu
 */
@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //需要前后端分离才设置，如需要可以忽略
        securityManager.setSessionManager(sessionManager());
        //设置realm（推荐放到最后，不然某些情况会不生效）
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * 自定义realm
     * @return
     */
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm=new CustomRealm();
        //customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager=new CustomSessionManager();
        //超时时间，默认30分钟，会话超时；方法里面的单位是毫秒
        customSessionManager.setGlobalSessionTimeout(20000);
        return customSessionManager;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置散列算法：这里使用md5算法
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数，散列2次，相当于md5(md5(xxx))
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("执行 shiroFilterFactoryBean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录拦截(如果没有前后端分离，跳转登录页面，分离跳转到接口提示)
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");
        //成功跳转(如果没有前后端分离，跳转成功页面，分离跳转到接口提示)
        shiroFilterFactoryBean.setSuccessUrl("/");
        //没有权限，未授权调用接口
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");
        //拦截器路径，坑一，部分路径无法进行拦截，时有时无；因为hashmap是无序，应该改为linkedHashMap
        Map<String,String> filterChainDefinitonMap =new LinkedHashMap<>();

        //退出登录过滤器
        filterChainDefinitonMap.put("/logout","logout");
        //匿名访问
        filterChainDefinitonMap.put("/pub/**","anon");
        //登录用户才可以访问
        filterChainDefinitonMap.put("/authc/**","authc");
        //管理员角色才可以访问
        filterChainDefinitonMap.put("/admin/**","roles[admin]");
        //有编辑权限才可以访问
        filterChainDefinitonMap.put("/video/update","perms[video_update]");

        //坑二：过滤链是顺序执行，从上而下，一般将/**放到最下面
        //anon:url可以匿名访问
        //authc:url 定义必须通过认证才可以访问
        filterChainDefinitonMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitonMap);

        return shiroFilterFactoryBean;
    }
}
