package com.example.demo.dao;

import com.example.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 在多条件动态查询时需要继承JpaSpecificationExecutor接口
 * JpaSpecificationExecutor可以通过findAll方法传入SimpleSpecification来进行查询
 * Created by BFD-593 on 2017/8/8.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>, JpaSpecificationExecutor {
    /**
     * where username = ? and password = ?
     * @param username
     * @param password
     * @return
     */
    UserInfo findByUsernameAndPassword(String username,String password);

    /**
     * where username = ? or password = ?
     * @param username
     * @param password
     * @return
     */
    List<UserInfo> findByUsernameOrPassword(String username,String password);

    /**
     * where username like ?
     * @param username
     * @return
     */
    List<UserInfo> findByUsernameLike(String username);

    /**
     * where username not like ?
     * @param username
     * @return
     */
    List<UserInfo> findByUsernameNotLike(String username);
    /**
     * where id between id1 and id2 and username = ?
     * @param id1
     * @param id2
     * @param username
     * @return
     */
    UserInfo findByIdBetweenAndUsername(Integer id1,Integer id2,String username);

    /**
     * where id > ?
     * @param id
     * @return
     */
    List<UserInfo> findByIdLessThan(Integer id);

    /**
     * where id < ?
     * @param id
     * @return
     */
    List<UserInfo> findByIdGreaterThan(Integer id);

    /**
     * where username is null;
     * @return
     */
    List<UserInfo> findByUsernameIsNull();

    /**
     * where username is not null
     * @return
     */
    List<UserInfo> findByUsernameIsNotNull();

    /**
     * order by id desc;
     * @return
     */
    List<UserInfo> findByOrderByIdDesc();

    /**
     * where id <> ?
     * @param id
     * @return
     */
    List<UserInfo> findByIdNot(Integer id);

    /**
     * where id in (?,?,?)
     * @param ids
     * @return
     */
    List<UserInfo> findByIdIn(List<Integer> ids);

    /**
     * where id not in (?,?,?)
     * @param ids
     * @return
     */
    List<UserInfo> findByIdNotIn(List<Integer> ids);

    /**
     * 其实Spring data 绝大部分的SQL都可以根据方法名定义的方式来实现，
     * 但是由于某些原因我们想使用自定义的SQL来查询，spring data也是完美支持的；
     * 在SQL的查询方法上面使用@Query注解，如涉及到删除和修改在需要加上@Modifying.
     * 也可以根据需要添加 @Transactional 对事物的支持，查询超时的设置等
     * @param username
     * @return
     */
    @Query("select count(u.id) from UserInfo u  where u.username like ?1")
    @Transactional(timeout = 10)
    int findCount(String username);

    @Modifying
    @Query("update UserInfo u set u.username=?1,u.password=?2 where u.id=?3 ")
    int modifyById(String username, String password, Integer id);

}
