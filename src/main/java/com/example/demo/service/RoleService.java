package com.example.demo.service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.model.Role;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param Id
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
     * @param Id
     * @return
     */
    public List<Role> listByOwn(String roleName,Integer Id) {
            Map<String,Object> map1 = Maps.newHashMap();
            Map<String,Object> map2 = Maps.newHashMap();
            map1.put("roleName",roleName);
            map2.put("id", Id);
            return roleRepository.listByOwn(map1, map2);
    }
}
