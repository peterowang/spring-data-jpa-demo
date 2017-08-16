package com.example.demo;


import com.example.demo.dao.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 我们使用通用repository时
 * 我们需要让spring在加载的时候找到我们自定义的BaseRepositoryFactoryBean的工厂，
 * 只要在入口类中加入@EnableJpaRepositories即可，代码如下
 */
@EnableJpaRepositories(basePackages = {"com.example.demo.dao"},
repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)//我们自己的工厂
@SpringBootApplication
public class SpringBootJpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}
}
