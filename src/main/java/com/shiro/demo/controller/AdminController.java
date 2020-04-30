package com.shiro.demo.controller;

import com.shiro.demo.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-30 17:42
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("/video/order")
    public JsonData findMyPlayRecord(){
        HashMap<String, String> recordMap = new HashMap<>();
        recordMap.put("abc","123222");
        recordMap.put("efg","45633");
        recordMap.put("hij","789444");
        recordMap.put("lmn","012666");
        return JsonData.buildSuccess(recordMap);
    }

}
