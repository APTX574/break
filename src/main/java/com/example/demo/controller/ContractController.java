package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Contract;
import com.example.demo.entity.Opt;
import com.example.demo.entity.Result;
import com.example.demo.service.ContractService;
import com.example.demo.service.OptService;
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
    @Autowired
    OptService optService;

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        String userName = json.getString("userName");
        String reason = json.getString("reason");
        String seriousness = json.getString("seriousness");
        String beiZhu = json.getString("beiZhu");
        String outLevel = json.getString("outLevel");
        Contract contract = new Contract();
        contract.setReason(reason).setUserName(userName).setCreateTime(new Date()).
                setStatus(STATUS_UNCONFIRMED).setBeiZhu(beiZhu).setOutLevel(outLevel)
                .setSeriousness(seriousness);
        contractService.insertContract(contract);
        Opt opt = new Opt();
        opt.setContractId(contract.getId()).setOptType(OPT_ADD).setCreateTime(new Date());
        optService.addOpt(opt);
        return Result.newSuccessfulResult("添加成功");
    }


    @ResponseBody
    @RequestMapping(value = "getbyid", method = RequestMethod.POST)
    public String getContractById(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        List<Contract> contract = contractService.getContractById(json.getInteger("id"));
        Map<String, Object> map = new HashMap<>(2);
        map.put("contractList", contract);
        return Result.newSuccessfulResult(map);
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(@RequestBody String body) {
        JSONObject json = JSONObject.parseObject(body);
        List<Contract> contract = contractService.getContractByTitle(json.getString("wd"));
        Map<String, Object> map = new HashMap<>(2);
        map.put("contractList", contract);
        return Result.newSuccessfulResult(map);
    }


    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getContractCon(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        String status = jsonObject.getString("status");
        Map<String, Object> map = new HashMap<>(4);
        int type = switch (status) {
            case "unconfirmed" -> STATUS_UNCONFIRMED;
            case "remakeconfirmed" -> STATUS_REMAKE_CONFIRMED;
            case "remakeunconfirmed" -> STATUS_REMAKE_UNCONFIRMED;
            default -> -1;
        };
        List<Contract> contract = contractService.getByStatus(type);
        map.put("contractList", contract);
        return Result.newSuccessfulResult(map);
    }

    @ResponseBody
    @RequestMapping(value = "change", method = RequestMethod.POST)
    public String change(@RequestBody String body) {

        JSONObject jsonObject = JSONObject.parseObject(body);
        int id = jsonObject.getInteger("id");
        String optCode = jsonObject.getString("opt");
        List<Contract> contractById = contractService.getContractById(id);
        Contract contract1 = contractById.get(0);
        int status = contract1.getStatus();
        int type;
        int optType;
        if ("confirm".equals(optCode)) {
            type = STATUS_UNCONFIRMED;
            optType = OPT_CONFIRM;
        } else if ("remake".equals(optCode)) {
            type = STATUS_REMAKE_UNCONFIRMED;
            optType = OPT_REMAKE;
        } else if ("refuse".equals(optCode)) {
            if (status == STATUS_UNCONFIRMED) {
                optType = OPT_REFUSE_CONFIRM;
            } else {
                optType = OPT_REFUSE_REMAKE;
            }
            type = -1;
        } else {
            return Result.newFailedResult("操作错误");
        }
        Opt opt = new Opt();
        opt.setContractId(contract1.getId()).setCreateTime(new Date());
        if ("refuse".equals(optCode) && status != STATUS_REMAKE_CONFIRMED) {
            opt.setOptType(optType);
            optService.addOpt(opt);
            contractService.updateContract(contract1);
            contract1.setStatus(STATUS_UNCONFIRMED);
            return Result.newSuccessfulResult("修改更改");

        }
        if (status == type) {
            opt.setOptType(optType);
            optService.addOpt(opt);
            contract1.setStatus(getNext(status));
            contractService.updateContract(contract1);
            return Result.newSuccessfulResult("修改更改");
        }

        return Result.newFailedResult("操作错误");
    }
}
