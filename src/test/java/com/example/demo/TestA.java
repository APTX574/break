package com.example.demo;


import com.example.demo.entity.Test;
import org.springframework.beans.factory.support.ScopeNotActiveException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author aptx
 * @date 2022/06/25 17:27
 */
public class TestA {
    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        list.add(new Test(1, "adad", new Date()));
        list.add(new Test(2, "adaszxd", new Date()));
        list.add(new Test(3, "adsdzx", new Date()));
        list.add(new Test(4, "adasdzx", new Date()));
        list.add(new Test(5, "adasd", new Date()));
        Stream<Test> collect = list.stream();
        collect.collect(Collectors.toMap(Test::getName, test -> test));

        collect = list.stream();
        Optional<Test> reduce = collect.reduce((test1, test2) -> new Test(test2.getId() + test1.getId(), null, null));
        System.out.println(reduce.get());
        Optional<Test> max = list.stream().max((Comparator.comparingInt(Test::getId)));
        System.out.println(max.get());
        list.stream().allMatch(test -> test.getId() == 1);
        Stream<Test> testStream = list.stream().filter(test -> test.getId() == 1);
        testStream.forEach(System.out::println);
        list.stream().sorted((o1,o2)->-o1.getId()+o2.getId()).forEach(System.out::println);
    }

}
