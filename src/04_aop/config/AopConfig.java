package pers.dreamer07.springAoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pers.dreamer07.springAoon.aspect.LogAspect;
import pers.dreamer07.springAoon.service.BookService;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-02
 **/
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }

    @Bean
    public BookService bookService(){
        return new BookService();
    }

}
