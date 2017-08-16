package com.example.demo.web;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BFD-593 on 2017/8/16.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("sql")
    public List<Role> listBySql(@RequestParam("flag") String flag, @RequestParam("roleName") String roleName) {
        return roleService.listBySql(flag, roleName);
    }
    @RequestMapping("dynamic")
    public List<Role> listByOwn(@RequestParam("id")Integer id, @RequestParam("roleName") String roleName) {
        return roleService.listByOwn(roleName,id);
    }
}
