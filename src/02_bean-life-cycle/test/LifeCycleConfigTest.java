package pers.dreamer07.springAoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.dreamer07.springAoon.bean.Person;

/**
 * @author EMTKnight
 * @create 2021-02-26
 */
@SpringBootTest
public class LifeCycleConfigTest {


    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        System.out.println("IOC 容器创建完成");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            Object bean = context.getBean(name);
            System.out.println(bean);
        }
        // 关闭容器
        context.close();
    }

}
