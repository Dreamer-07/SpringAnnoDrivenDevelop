package pers.dreamer07.springAoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.dreamer07.springAoon.service.BookService;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-02
 **/
@SpringBootTest
public class AopConfigTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

    /**
     * 测试 Aop 环绕
     */
    @Test
    public void test01(){
        BookService bookService = context.getBean(BookService.class);
        bookService.div(0, 2);
    }

}
