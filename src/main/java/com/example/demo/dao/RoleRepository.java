package com.example.demo.dao;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 在多条件动态查询时需要继承JpaSpecificationExecutor接口
 * JpaSpecificationExecutor可以通过findAll方法传入SimpleSpecification来进行查询
 * Created by BFD-593 on 2017/8/16.
 */
public interface RoleRepository extends BaseRepository<Role,Integer>,JpaSpecificationExecutor<Role> {
    /**
     * 原来JPARepository的方法依然可以使用
     * */
    List<Role> findByRoleNameLike(String roleName);
}
