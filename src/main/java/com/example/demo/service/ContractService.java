package com.example.demo.service;

import com.example.demo.dao.ContractMapper;
import com.example.demo.entity.Contract;
import com.example.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Fuseable;

import java.util.List;

/**
 * @author aptx
 * @date 2022/06/26 22:03
 */
@Service
public class ContractService implements Constant {
	@Autowired
	private ContractMapper contractMapper;

	public int insertContract(Contract contract) {
		contractMapper.insertContract(contract);
		return 1;
	}

	public int updateContract(Contract contract) {
		contractMapper.updateContract(contract);
		return 1;
	}

	public List<Contract> getContract() {
		return contractMapper.getContract();
	}

	public List<Contract> getContractById(int id) {
		return contractMapper.getContractById(id);
	}

	public List<Contract> getContractByTitle(String title) {
		System.out.println(title);
		return contractMapper.getContractByTitle("%" + title + "%");
	}

	public List<Contract> getUnconfirmedContract() {
		return contractMapper.getContractByStatus(STATUS_UNCONFIRMED);
	}

	public List<Contract> getConfirmedContract() {
		return contractMapper.getContractByStatus(STATUS_REMAKE_UNCONFIRMED);

	}

	public List<Contract> getRefuseContract() {
		return contractMapper.getContractByStatus(STATUS_REFUSE);
	}

	public List<Contract> getByStatus(int status) {
		if (status == -1) {
			return getContract();
		}
		return contractMapper.getContractByStatus(status);

	}
}
