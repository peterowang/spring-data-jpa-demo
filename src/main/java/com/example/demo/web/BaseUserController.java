package com.example.demo.web;

import com.example.demo.model.UserInfo;
import com.example.demo.service.BaseUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by BFD-593 on 2017/8/14.
 */
@RestController
@RequestMapping("/base")
public class BaseUserController {
    @Autowired
    private BaseUserInfoService baseUserInfoService;
    @RequestMapping(value="findand",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public UserInfo findByUsernameAndPassword(@RequestParam("username")String username,@RequestParam("password")String password){
       return baseUserInfoService.findUserInfoByUsernameAndPassword(username, password);
    }
    @RequestMapping(value="findor",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public List<UserInfo> findByUsernameOrPassword(@RequestParam("username")String username,@RequestParam("password")String password){
        return baseUserInfoService.findUserInfoByUsernameOrPassword(username, password);
    }
    @RequestMapping(value="like",method = RequestMethod.GET,produces = {"application/json;charset"})
    public List<UserInfo> findByUsernameLike(@RequestParam("flag")String flag,@RequestParam("username")String username){
        return baseUserInfoService.findByUsernameLike(flag,"%"+username+"%");
    }
    @RequestMapping(value="between",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public UserInfo findByIdBetweenAndUsername(@RequestParam("id1")Integer id1,@RequestParam("id2")Integer id2,@RequestParam("username")String username){
        return baseUserInfoService.findByIdBetweenAndUsername(id1,id2,username);
    }
    @RequestMapping(value="than",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public List<UserInfo> findThan(@RequestParam("id")Integer id,@RequestParam("flag")String flag){
        return baseUserInfoService.findThan(flag, id);
    }
    @RequestMapping(value="null",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public List<UserInfo> findNull(@RequestParam("flag")String flag){
        return baseUserInfoService.findNull(flag);
    }
    @RequestMapping(value="order",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public List<UserInfo> findOrderBy(){
        return baseUserInfoService.findOrderBy();
    }
    @RequestMapping(value="not",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public List<UserInfo> findByIdNot(Integer id){
        return baseUserInfoService.findByIdNot(id);
    }
    @RequestMapping(value="in",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public List<UserInfo> findByIdInOrNot(@RequestParam("flag") String flag,@RequestParam("ids") List<Integer> ids){
        return baseUserInfoService.findByIdInOrNot(flag, ids);
    }
    @RequestMapping(value="count",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public int findByIdInOrNot(@RequestParam("username") String username){
        return baseUserInfoService.findCount(username);
    }
    @RequestMapping(value="update",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public int findByIdInOrNot(@RequestParam("username") String username,@RequestParam("password") String password
    ,@RequestParam("id") Integer id){
        return baseUserInfoService.updateUserInfo(username, password,id);
    }
}
