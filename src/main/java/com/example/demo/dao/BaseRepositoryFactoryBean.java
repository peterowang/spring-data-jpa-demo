package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 我们需要创建一个自定义的工厂，
 * 在这个工厂中注册我们自己定义的BaseRepositoryImpl的实现。
 * 这个工厂的写法具体参照Spring Data的JpaRepositoryFactoryBean和JpaRepositoryFactory。
 * 这个类上面一堆的泛型，我们不用考虑，只要按照相同的方式来写即可。
 * Created by BFD-593 on 2017/8/16.
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T,
        I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {
    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    /**
     * 此方法是JpaRepositoryFactoryBean中的，
     * 目的是返回一个工厂,我们调用它来反回我们自己的工厂
     * @param entityManager
     * @return
     */
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
            return new BaseRepositoryFactory(entityManager);
    }
    //创建一个内部类，该类不用在外部访问
    private static class BaseRepositoryFactory<T,I extends Serializable> extends JpaRepositoryFactory{
            private final EntityManager entityManager;
            public BaseRepositoryFactory(EntityManager entityManager){
                super(entityManager);
                this.entityManager=entityManager;
            }

        /**
         * 通过这两个方法来确定具体的实现类，JpaRepositoryFactory中的方法
         * 也就是Spring Data Jpa具体实例化一个接口的时候会去创建的实现类。
         * Spring Data JPA都是调用SimpleJpaRepository来创建实例。以下是我们自己的工厂实现的代码
         * @param information
         * @return
         */
        //设置具体的实现类是BaseRepositoryImpl
        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl<T, I>((Class<T>)information.getDomainType(), entityManager);
        }
        //设置具体的实现类的class
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}
