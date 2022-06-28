package com.example.demo.dao;

import com.example.demo.entity.Opt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author aptx
 * @date 2022/06/29 01:09
 */
@Mapper
public interface OptMapper {
    int insertOpt(Opt opt);

    List<Opt> selectOptByType(int type);
    List<Opt> selectOptByCon(int id);
}
