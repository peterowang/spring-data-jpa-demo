package com.example.demo.service;

import com.example.demo.dao.UserInfoRepository;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by BFD-593 on 2017/8/8.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BaseUserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo findUserInfoByUsernameAndPassword(String username,String password){
        return userInfoRepository.findByUsernameAndPassword(username, password);
    }

    public List<UserInfo> findByUsernameLike(String flag,String username){
        return flag.equals("1") ? userInfoRepository.findByUsernameLike(username) : userInfoRepository.findByUsernameNotLike(username);
    }
    public List<UserInfo> findUserInfoByUsernameOrPassword(String username,String password){
        return userInfoRepository.findByUsernameOrPassword(username, password);
    }
    public UserInfo findByIdBetweenAndUsername(Integer id1,Integer id2,String username){
        return userInfoRepository.findByIdBetweenAndUsername(id1, id2, username);
    }
    public List<UserInfo> findThan(String flag,Integer id){
        return flag.equals("1") ? userInfoRepository.findByIdLessThan(id) : userInfoRepository.findByIdGreaterThan(id);
    }
    public List<UserInfo> findNull(String flag){
        return flag.equals("1") ? userInfoRepository.findByUsernameIsNotNull() : userInfoRepository.findByUsernameIsNull();
    }
    public List<UserInfo> findOrderBy(){
        return userInfoRepository.findByOrderByIdDesc();
    }
    public List<UserInfo> findByIdNot(Integer id){
        return userInfoRepository.findByIdNot(id);
    }
    public List<UserInfo> findByIdInOrNot(String flag,List<Integer> ids){
        return flag.equals("1") ? userInfoRepository.findByIdIn(ids) : userInfoRepository.findByIdNotIn(ids);
    }
    public int findCount(String username){
        return userInfoRepository.findCount("%"+username+"%");
    }
    public int updateUserInfo(String username,String password,Integer id){
        return userInfoRepository.modifyById(username, password,id);
    }
}
