package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Contract;
import com.example.demo.entity.Result;
import com.example.demo.service.ContractService;
import com.example.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestBody String body) {
		JSONObject json = JSONObject.parseObject(body);
		int id = json.getInteger("id");
		int status = json.getInteger("status");
		if (status != STATUS_UNCONFIRMED  && status != STATUS_REFUSE) {
			status = STATUS_UNCONFIRMED;
		}
		Contract contract = new Contract();
		contract.setStatus(status).setId(id);
		contractService.updateContract(contract);
		return Result.newSuccessfulResult("修改成功");
	}

	@ResponseBody
	@RequestMapping(value = "get/{status}", method = RequestMethod.POST)
	public String getContractCon(@RequestBody String body) {
		JSONObject jsonObject = JSONObject.parseObject(body);
		String status = jsonObject.getString("status");
		Map<String, Object> map = new HashMap<>();
		int type;
		switch (status) {
//			case "confirmed":
//				type = STATUS_CONFIRMED;
//				break;
			case "unconfirmed":
				type = STATUS_UNCONFIRMED;
				break;
			case "remakeconfirmed":
				type = STATUS_REMAKE_CONFIRMED;
				break;
			case "remakeunconfirmed":
				type = STATUS_REMAKE_UNCONFIRMED;
				break;
			default:
				type = -1;
		}
		List<Contract> contract = contractService.getByStatus(type);
		map.put("contractList", contract);
		return Result.newSuccessfulResult(map);
	}

	@ResponseBody
	@RequestMapping(value = "change", method = RequestMethod.POST)
	public String change(@RequestBody String body) {

		JSONObject jsonObject = JSONObject.parseObject(body);
		int id = jsonObject.getInteger("id");
		String opt = jsonObject.getString("opt");
		List<Contract> contractById = contractService.getContractById(id);
		Contract contract1 = contractById.get(0);
		int status = contract1.getStatus();
		int type;
		if ("confirm".equals(opt)) {
			type = STATUS_UNCONFIRMED;
		} else if ("remake".equals(opt)) {
			type = STATUS_REMAKE_UNCONFIRMED;
		} else if ("refuse".equals(opt)) {
			type = -1;
		} else {
			return Result.newFailedResult("操作错误");
		}
		if ("refuse".equals(opt) && status != STATUS_REMAKE_CONFIRMED) {
			contract1.setStatus(STATUS_UNCONFIRMED);
			contractService.updateContract(contract1);
			return Result.newSuccessfulResult("修改更改");

		}
		if (status == type) {
			contract1.setStatus(getNext(status));
			contractService.updateContract(contract1);
			return Result.newSuccessfulResult("修改更改");
		}

		return Result.newFailedResult("操作错误");
	}


}
