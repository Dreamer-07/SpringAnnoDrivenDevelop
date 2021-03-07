package pers.dreamer07.springAoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-04
 **/
@EnableTransactionManagement // 开启事务注解
@Configuration
@ComponentScan("pers.dreamer07.springAoon")
public class TxConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        /*
         * 使用 Spring JDBC 自带的事务管理器，且还需要配置对应的数据源
         * Spring 中的配置类中，当注册 @Bean 组件时使用的方法不会直接调用，而是从 Spring IOC 中获取
         * */
        return new DataSourceTransactionManager(dataSource);
    }



}
