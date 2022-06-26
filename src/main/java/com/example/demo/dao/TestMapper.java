package com.example.demo.dao;

import com.example.demo.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author aptx
 * @date 2022/06/25 15:39
 */
@Mapper
public interface TestMapper {
    List<Test> getTests();
    List<Test> getTestByName(@Param("name") String name);
    int insertTest(Test test);

}
