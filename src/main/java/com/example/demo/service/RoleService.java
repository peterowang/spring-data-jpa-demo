package com.example.demo.service;

import com.example.demo.SpecificationUtil.SimpleSpecificationBuilder;
import com.example.demo.dao.RoleRepository;
import com.example.demo.model.Role;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

/**
 * Created by BFD-593 on 2017/8/16.
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 使用自定义baseRepository中的listBysql
     * 根据自定义sql查询
     * @param flag
     * @param roleName
     * @return
     */
    public List<Role> listBySql(String flag, String roleName) {
        return flag.equals("1") ? roleRepository.findByRoleNameLike("%" + roleName + "%")
                : roleRepository.listBySql("select * from t_role where id >=1");
    }

    /**
     * 使用自定义baseRepository中的listByOwn
     * 根据自定义多条件动态查询
     * @param roleName
     * @param id
     * @return
     */
    public List<Role> listByOwn(String roleName,Integer id) {
            Map<String,Object> map1 = Maps.newHashMap();
            Map<String,Object> map2 = Maps.newHashMap();
            map1.put("roleName",roleName);
            map2.put("id", id);
            return roleRepository.listByOwn(map1, map2);
    }

    /**
     * 测试封装的specification
     * 实现简单风格的动态查询
     * id < id and roleName like %roleName% or id>id and roleName like roleName%的动态查询
     * 某个参数为空时，就不使用该参数所在的条件。
     * @param roleName
     * @param id
     * @return
     */
    public List<Role> spe(String roleName,Integer id) {
        return roleRepository.findAll(new SimpleSpecificationBuilder<Role>().
                and("id", "<", id).
                and("roleName",":",roleName).
                or("id",">",id).
                and("roleName","l:",roleName).
                getSpecification());
    }
}
