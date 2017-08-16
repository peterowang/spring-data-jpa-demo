package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 通用repository
 * 我们使用它来简化我们的一些repository的通用CRUD
 * 不需要在每一个repository中写CRUD,只需在需要的repository上继承就好。
 * @NoRepositoryBean，这个表示该接口不会创建这个接口的实例，像UserInfoRepository等，
 * 只要是在jpaConfig里配置的基础名里的接口全会被实例化。
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID>{
    //自定义sql查询
    List<T> listBySql(String sql);
    //自定义多条件动态查询
    List<T> listByOwn(Map<String,Object> map1,Map<String,Object> map2);
}
