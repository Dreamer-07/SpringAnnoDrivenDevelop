package pers.dreamer07.springAoon.controller;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import pers.dreamer07.springAoon.service.BookService;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Spring 支持 @Resource(JSR 250) 和 @Inject(JSR 330) 注解实现自动装配
 * @author EMTKnight
 * @create 2021-02-28
 */
@Controller
@ToString
@Getter
public class BookController {

    /*
     * 可以和 @Autowired 注解一样实现自动装配
     *      也是默认通过对应的属性名作为 id 进行装配的
     *      但可以通过指定 name 属性值装配对应的 bean 实例
     * 但和其不同的是：
     *      1. 不支持 @Qualifier 和 @Primary
     *      2. 无法使用类似于 @Autowired(required=false) 的功能
    * */
    // @Resource(name = "bookService2")
    // @Qualifier("bookService2")
    /*
    * 可以和 @Autowired 注解一样实现自动装配
    *       也是默认通过对应的属性名作为 id 进行装配的
    *       支持和 @Primary 注解和 @Qualifier 注解
    * 和其不同的是：无法指定任何属性
    * */
    @Inject
    private BookService bookService;

}
