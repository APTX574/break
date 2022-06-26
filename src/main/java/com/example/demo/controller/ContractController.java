package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Contract;
import com.example.demo.entity.Result;
import com.example.demo.service.ContractService;
import com.example.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aptx
 * @date 2022/06/26 22:21
 */
@Controller
public class ContractController implements Constant {
    @Autowired
    ContractService contractService;

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        String title = json.getString("title");
        String reason = json.getString("reason");
        Contract contract = new Contract();
        contract.setReason(reason).setTitle(title).setCreateTime(new Date()).setStatus(STATUS_UNCONFIRMED).setType(TYPE_TYPE1);
        contractService.insertContract(contract);
        return Result.newSuccessfulResult("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getContract(@RequestBody String body) {
        List<Contract> contract = contractService.getContract();
        Map<String, Object> map = new HashMap<>();
        map.put("contractList", contract);
        return Result.newSuccessfulResult(map);
    }

    @ResponseBody
    @RequestMapping(value = "getbyid", method = RequestMethod.POST)
    public String getContractById(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        List<Contract> contract = contractService.getContractById(json.getInteger("id"));
        Map<String, Object> map = new HashMap<>();
        map.put("contractList", contract);
        return Result.newSuccessfulResult(map);
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        List<Contract> contract = contractService.getContractByTitle(json.getString("wd"));
        Map<String, Object> map = new HashMap<>();
        map.put("contractList", contract);
        return Result.newSuccessfulResult(map);
    }


}
