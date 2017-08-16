package com.example.demo.dao;

import com.example.demo.model.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *这个实现类比较的简单，首先我们需要继承SimpleJpaRepository，
 * SimpleJpaRepository帮助我们实现了JpaRepository中的方法。
 * 然后实现BaseRepository接口。listBySQL方法非常的简单,具体的作用就是执行一条sql返回一组投影的列表。
 * Created by BFD-593 on 2017/8/16.
 */
public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID>
        implements BaseRepository<T,ID>{
    private final EntityManager entityManager;

    //父类没有不带参数的构造方法，这里手动构造父类
    public BaseRepositoryImpl(Class<T> domainClass,EntityManager entityManager){
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }
    //通过EntityManager来完成查询,指定sql来查询
    @Override
    public List<T> listBySql(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<T> listByOwn(final Map<String,Object> map1, final Map<String,Object> map2) {
        Specification<T> sf = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                String key1 = (String) map1.keySet().toArray()[0];
                String key2 = (String) map2.keySet().toArray()[0];
                if (StringUtils.isNotEmpty(key1)) {
                    list.add(criteriaBuilder.like(root.get(key1).as(String.class),"%"+map1.get(key1)+"%"));
                }
                if(StringUtils.isNotEmpty(key2)){
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key2).as(Integer.class), (Integer) map2.get(key2)));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return criteriaQuery.getRestriction();
            }
        };
        return findAll(sf);
    }
}
