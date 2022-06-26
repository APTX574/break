package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Result;
import com.example.demo.entity.Test;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author aptx
 * @date 2022/06/25 15:42
 */
@Controller
public class TestController {
    @Autowired
    TestService testService;


    @ResponseBody
    @RequestMapping("/test")
    String getTestByName(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        List<Test> testList = testService.getTestByName(json.getString("name"));
        return Result.newSuccessfulResult(testList);
    }

    @ResponseBody
    @RequestMapping("/insert")
    String insertTest(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        Test test = new Test();
        test.setName(json.getString("name"));
        test.setCreateTime(new Date());
        int result = testService.insertTest(test);
        return Result.newSuccessfulResult(result);
    }
}
