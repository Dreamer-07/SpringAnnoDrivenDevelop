package pers.dreamer07.springAoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pers.dreamer07.springAoon.bean.Person;

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
@Import(MyBeanFactoryPostProcessor.class)
public class ExpConfig {

    @Bean
    public Person person(){
        return new Person();
    }

}
