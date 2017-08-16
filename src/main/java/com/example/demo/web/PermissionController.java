package com.example.demo.web;

import com.example.demo.model.Permission;
import com.example.demo.model.Role;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jnlp.PersistenceService;
import java.util.List;

/**
 * Created by BFD-593 on 2017/8/16.
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("dynamic")
    public List<Permission> listByOwn(@RequestParam("id")Integer id, @RequestParam("permissionName") String permissionName) {
        return permissionService.listByOwn(permissionName,id);
    }
}
