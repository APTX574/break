package com.example.demo.service;

import com.example.demo.dao.TestMapper;
import com.example.demo.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.nativex.hint.SerializationHint;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aptx
 * @date 2022/06/25 15:47
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public List<Test> getTestByName(String name) {
        return testMapper.getTestByName(name);
    }

    public int insertTest(Test test) {
        return testMapper.insertTest(test);
    }
}
