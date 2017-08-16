package com.example.demo.dao;

import com.example.demo.model.Role;

import java.util.List;

/**
 * Created by BFD-593 on 2017/8/16.
 */
public interface RoleRepository extends BaseRepository<Role,Integer> {
    /**
     * 原来JPARepository的方法依然可以使用
     * */
    List<Role> findByRoleNameLike(String roleName);
}
