package com.example.demo.service;

import com.example.demo.dao.OptMapper;
import com.example.demo.entity.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aptx
 * @date 2022/06/29 01:13
 */
@Service
public class OptService {
    @Autowired
    OptMapper optMapper;

    public int addOpt(Opt opt) {
        return optMapper.insertOpt(opt);
    }

    public List<Opt> getOptByType(int type) {
        return optMapper.selectOptByType(type);
    }

    public List<Opt> getOptByConId(int id) {
        return optMapper.selectOptByCon(id);
    }
}
