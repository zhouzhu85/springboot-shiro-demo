package com.shiro.demo.controller;

import com.shiro.demo.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-30 17:50
 */
@RestController
@RequestMapping("video")
public class VideoController {
    @RequestMapping("update")
    public JsonData update(){
        return JsonData.buildSuccess("更新成功");
    }
}
