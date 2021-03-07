package pers.dreamer07.springAoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-05
 **/
@SpringBootTest
public class ExpConfigTest {

    @Test
    public void test01(){
        // MyBeanFactoryPostProcessor.postProcessBeanFactory() 方法执行早于 Person 组件的创建
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExpConfig.class);
        context.close();
    }

}
