package com.example.demo.dao;

import com.example.demo.model.Permission;

import java.io.Serializable;

/**
 * 这样我就可以使用baseRepository中的
 *  自定义sql查询
    List<T> listBySql(String sql);
    自定义多条件动态查询
    List<T> listByOwn(Map<String,Object> map1,Map<String,Object> map2);
    而不需要自己去写了
 * Created by BFD-593 on 2017/8/16.
 */
public interface PermissionRepository extends BaseRepository<Permission,Integer>{

}
