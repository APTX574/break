package com.example.demo.dao;

import com.example.demo.entity.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author aptx
 * @date 2022/06/26 22:09
 */
@Mapper
public interface ContractMapper {
    int insertContract(Contract contract);

    int updateContract(Contract contract);

    List<Contract> getContract();

    List<Contract> getContractById(int id);

    List<Contract> getContractByTitle(String title);
    List<Contract> getContractByStatus(@Param("status") int status);
}
