package com.andx.micro.support.jpa;

import com.alibaba.druid.pool.DruidDataSource;
import com.andx.micro.support.jpa.model.TmpJpa;
import com.andx.micro.support.jpa.repository.TmpJpaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by andongxu on 17-1-4.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.andx.micro.support.jpa.model")
public class TmpJpaTest extends BaseTest{

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }


//    @Bean
//    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////        vendorAdapter.setGenerateDdl(generateDdl);
////        vendorAdapter.setShowSql(showSql);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
////        factory.setPackagesToScan(getEntityPackage());
//        factory.setDataSource(dataSource);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }


//    @Autowired
//    TmpJpaRepository tmpJpaRepository;

    @Test
    public void t1() {
        System.out.println("-----------");
        TmpJpa tmpJpa = new TmpJpa();
        tmpJpa.setCola("aaaa");
//        tmpJpaRepository.save(tmpJpa);
        Assert.assertNotNull(tmpJpa);

    }
}
