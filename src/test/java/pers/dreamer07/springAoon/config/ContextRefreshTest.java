package pers.dreamer07.springAoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-10
 **/
@SpringBootTest
public class ContextRefreshTest {


    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        System.out.println(context);
        context.close();
    }

}
