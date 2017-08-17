package com.example.demo.SpecificationUtil;

import com.google.common.collect.Lists;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 创建一个SimpleSpecificationBuilder来具体创建SimpleSpecification,
 * 这里为了方便调用简单进行了一下设计。
 * Created by BFD-593 on 2017/8/17.
 */
public class SimpleSpecificationBuilder<T> {
    /**
     * 条件列表
     */
    private List<SpecificationOperator> opers;
    /**
     * 构造函数，初始化的条件是and
     */
    public SimpleSpecificationBuilder(String key,String oper,Object value,String join){
        SpecificationOperator so = new SpecificationOperator(key, value, oper, join);
        opers = Lists.newArrayList();
        opers.add(so);
    }

    /**
     * 构造，初始化无条件
     */
    public SimpleSpecificationBuilder(){
        opers = Lists.newArrayList();
    }

    /**
     * 往list中填加条件
     * @param key
     * @param oper
     * @param value
     * @param join
     * @return
     */
    public SimpleSpecificationBuilder add(String key,String oper,Object value,String join){
        SpecificationOperator so = new SpecificationOperator(key, value, oper, join);
        opers.add(so);
        return this;
    }

    /**
     * 填加一个and条件
     * @param key
     * @param oper
     * @param value
     * @return
     */
    public SimpleSpecificationBuilder and(String key,String oper,Object value){
        return this.add(key, oper, value, "and");
    }

    /**
     * 填加一个or条件
     * @param key
     * @param oper
     * @param value
     * @return
     */
    public SimpleSpecificationBuilder or(String key,String oper,Object value){
        return this.add(key, oper, value, "or");
    }
    /**
     * 触发SimpleSpecification并返回Specification
     */
    public Specification getSpecification(){
        Specification<T> sp = new SimpleSpecification<T>(opers);
        return sp;
    }
}
