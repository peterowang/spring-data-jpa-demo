package com.example.demo.service;

import com.example.demo.dao.PermissionRepository;
import com.example.demo.model.Permission;
import com.example.demo.model.Role;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BFD-593 on 2017/8/16.
 */
@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    public List<Permission> listByOwn(String permissionName, Integer Id) {
        Map<String,Object> map1 = Maps.newHashMap();
        Map<String,Object> map2 = Maps.newHashMap();
        map1.put("permissionName",permissionName);
        map2.put("id", Id);
        return permissionRepository.listByOwn(map1, map2);
    }
}
