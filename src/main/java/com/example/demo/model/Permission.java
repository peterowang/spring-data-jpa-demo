package com.example.demo.model;

import com.alibaba.fastjson.JSONObject;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by BFD-593 on 2017/8/16.
 */
@Entity
@Table(name = "t_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "permission_name")
    private String permissionName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
