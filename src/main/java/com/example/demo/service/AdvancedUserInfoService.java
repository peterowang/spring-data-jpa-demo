package com.example.demo.service;

import com.example.demo.dao.UserInfoRepository;
import com.example.demo.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BFD-593 on 2017/8/15.
 */
@Service
public class AdvancedUserInfoService{
    @Autowired
    UserInfoRepository userInfoRepository;
    /**
     * 简单分页排序查询
     */
    public Page<UserInfo> pageList(int pageNo,int pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable able = new PageRequest(pageNo, pageSize, sort);
        return userInfoRepository.findAll(able);
    }

    /**
     * 复杂动态多条件查询
     * @param username
     * @param password
     * @param id
     * @return
     */
    public List<UserInfo> listDynamic(final String username,final String password,final Integer id){
        Specification<UserInfo> sf = new Specification<UserInfo>() {
            List<Predicate> list = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
/*              Predicate p1 = cb.like(root.get("name").as(String.class), "%"+um.getName()+"%");
                Predicate p2 = cb.equal(root.get("uuid").as(Integer.class), um.getUuid());
                Predicate p3 = cb.gt(root.get("age").as(Integer.class), um.getAge());
                //把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的
                query.where(cb.and(p3,cb.or(p1,p2)));//where p3 and (p1 or p2)
                //添加排序的功能
                query.orderBy(cb.desc(root.get("uuid").as(Integer.class)));
                return query.getRestriction();*/
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(username)){
                    list.add(criteriaBuilder.like(root.get("username").as(String.class), "%" + username + "%"));
                }
                if(!StringUtils.isEmpty(password)){
                    list.add(criteriaBuilder.isNotNull(root.get("password").as(String.class)));
                }
                if(id!=null){
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("id").as(Integer.class),id));
                }
                Predicate[] pd = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pd));
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id").as(Integer.class)));
                return criteriaQuery.getRestriction();
            }
        } ;
        return userInfoRepository.findAll(sf);
    }
    public Page<UserInfo> pageDynamic(final String username,final String password,final Integer id1,
                                      final Integer id2,final Integer pageNo,final Integer pageSize){
        return userInfoRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1=null;Predicate p2=null; Predicate p3=null;
                if(StringUtils.isNotEmpty(username)){
                    p1 = criteriaBuilder.equal(root.get("username").as(String.class),username);
                }
                if(StringUtils.isNotEmpty(password)){
                    p2 = criteriaBuilder.equal(root.get("password").as(String.class), password);
                }
                if(id1!=null&&id2!=null){
                    p3 = criteriaBuilder.between(root.get("id").as(Integer.class), id1, id2);
                }
                criteriaQuery.where(criteriaBuilder.and(p1,criteriaBuilder.or(p2,p3)));
                return criteriaQuery.getRestriction();
            }
        },new PageRequest(pageNo,pageSize,new Sort(Sort.Direction.DESC,"id")));
    }
}
