package com.example.demo.service;

import com.example.demo.dao.ContractMapper;
import com.example.demo.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aptx
 * @date 2022/06/26 22:03
 */
@Service
public class ContractService {
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
        return contractMapper.getContractByTitle("@" + title + "@");
    }
}
