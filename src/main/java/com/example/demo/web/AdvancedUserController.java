package com.example.demo.web;

import com.example.demo.model.UserInfo;
import com.example.demo.service.AdvancedUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BFD-593 on 2017/8/15.
 */

@RestController
@RequestMapping("/advanced")
public class AdvancedUserController {
    @Autowired
    private AdvancedUserInfoService advancedUserInfoService;
    @RequestMapping(value="page",produces = {"application/json;charset"},method = RequestMethod.GET)
    public Page<UserInfo> pageList(int pageNo,int pageSize){
        return advancedUserInfoService.pageList(pageNo, pageSize);
    }
    @RequestMapping(value="listDynamic",produces = {"application/json;charset"},method = RequestMethod.GET)
    public List<UserInfo> dynamic(String username, String password, Integer id){
        return advancedUserInfoService.listDynamic(username, password, id);
    }
    @RequestMapping(value="pageDynamic",produces = {"application/json;charset"},method = RequestMethod.GET)
    public Page<UserInfo> dynamic(String username, String password, Integer id1,
                                   Integer id2, Integer pageNo, Integer pageSize){
        return advancedUserInfoService.pageDynamic(username, password, id1, id2, pageNo, pageSize);
    }
}
