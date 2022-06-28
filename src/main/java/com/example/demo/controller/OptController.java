package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Opt;
import com.example.demo.entity.Result;
import com.example.demo.service.OptService;
import com.example.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aptx
 * @date 2022/06/29 01:38
 */
@Controller
public class OptController implements Constant {
    @Autowired
    OptService optService;

    @ResponseBody
    @RequestMapping(value = "/get/opt", method = RequestMethod.POST)
    public String getOptByConId(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer id = jsonObject.getInteger("id");
        List<Opt> optByConId = optService.getOptByConId(id);
        return Result.newSuccessfulResult(handleOptList(optByConId));
    }

    public Map<String, Object> handleOptList(List<Opt> list) {
        Map<String, Object> map = new HashMap<>(4);
        list.forEach((opt) -> {
            String key;
            switch (opt.getOptType()) {
                case OPT_ADD -> key = "add";
                case OPT_CONFIRM -> key = "confirm";
                case OPT_REMAKE -> key = "remake";
                case OPT_REFUSE_REMAKE -> key = "remake_refuse";
                case OPT_REFUSE_CONFIRM -> key = "confirm_refuse";
                default -> key = "err";
            }
            map.put(key, opt.getCreateTime());
        });
        return map;
    }
}
