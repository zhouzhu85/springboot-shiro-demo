package com.shiro.demo.controller;

import com.shiro.demo.domain.JsonData;
import com.shiro.demo.domain.UserQuery;
import com.shiro.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-29 16:31
 */
@RestController
@RequestMapping("pub")
public class PublicController {

    @RequestMapping("need_login")
    public JsonData needLogin(){
        return JsonData.buildSuccess("请先登录，才能访问",2);
    }

    @RequestMapping("not_permit")
    public JsonData notPermit(){
        return JsonData.buildSuccess("拒绝访问，没有权限",-3);
    }
    @RequestMapping("index")
    public JsonData index(){
        List<String> videoList=new ArrayList<>();
        videoList.add("asdfsdf");
        videoList.add("色打发士大夫");
        return JsonData.buildSuccess(videoList);
    }

    /**
     * 登录接口
     * @param userQuery
     * @param request
     * @param response
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody UserQuery userQuery, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token=new UsernamePasswordToken(userQuery.getName(),userQuery.getPwd());
            subject.login(token);
            Map<String,Object> info=new HashMap<>();
            info.put("msg","登录成功");
            info.put("session_id",subject.getSession().getId());
            return JsonData.buildSuccess(info);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return JsonData.buildError("账号或密码错误");

        }
    }
}
