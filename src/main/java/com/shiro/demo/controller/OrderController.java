package com.shiro.demo.controller;

import com.shiro.demo.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-30 16:26
 */
@RestController
@RequestMapping("authc")
public class OrderController {
    @RequestMapping("/video/play_record")
    public JsonData findMyPlayRecord(){
        HashMap<String, String> recordMap = new HashMap<>();
        recordMap.put("abc","123");
        recordMap.put("efg","456");
        recordMap.put("hij","789");
        recordMap.put("lmn","012");
        return JsonData.buildSuccess(recordMap);
    }
}
