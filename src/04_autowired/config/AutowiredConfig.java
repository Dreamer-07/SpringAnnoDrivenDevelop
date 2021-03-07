package pers.dreamer07.springAoon.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.*;
import pers.dreamer07.springAoon.SpringAoonApplication;
import pers.dreamer07.springAoon.repository.BookRepository;
import pers.dreamer07.springAoon.service.BookService;

/**
 * 自动装配：Spring 通过依赖注入(DI).完成 IOC 容器中各个组件的依赖关系
 *
 * 注解开发
 *      1. @Autowired：自动装配
 *          使用：
 *              1. 如果容器中只有一个对应类型的 bean 实例，就使用其进行依赖注入(context.getBean(Class))
 *              2. 如果容器中有多个对象类型的 bean 实例，就将对应的属性名作为 id 寻找
 *                 注册时如果有多个重复 id 的 bean 实例，则会覆盖
 *              3. 可以配合 @Qualifier 注解指定 value 值为对应的 bean id
 *              4. 默认情况下使用该注解的属性容器中必须有对应的 bean 实例，否则就会报错
 *                 可以指定其 required 属性为 false，就不会报错
*               5. 可以配置 @Primary 注解指定对应的 bean 实例为 IOC 装配时使用的首选项
 *      2. @Qualifier：根据 id 在 IOC 容器中查找对应的 bean 实例
 *      3. @Primary：指定其注册组件为 IOC 容器自动装配时的首选项
 *
 * @author EMTKnight
 * @create 2021-02-27
 */
@Configuration
@ComponentScan(value = "pers.dreamer07.springAoon")
public class AutowiredConfig {

    @Bean("bookRepository2")
    public BookRepository bookRepository2(){
        return new BookRepository(2);
    }

    // @Primary
    @Bean("bookService2")
    public BookService bookService(){
        return new BookService();
    }
}
