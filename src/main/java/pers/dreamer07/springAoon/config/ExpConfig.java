package pers.dreamer07.springAoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pers.dreamer07.springAoon.bean.Person;
import pers.dreamer07.springAoon.listener.MyApplicationListener;

/**
 * 扩展原理
 *
 *
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-05
 **/
@Configuration
// @Import(MyBeanFactoryPostProcessor.class) // 测试 BeanFactoryPostProcessor 用
// @Import(MyBeanDefinitionRegistryPostProcessor.class) // 测试 BeanDefinitionRegistryPostProcessor 用
@Import(MyApplicationListener.class) // 测试 ApplicationListener
public class ExpConfig {

    @Bean
    public Person person(){
        return new Person();
    }

}
