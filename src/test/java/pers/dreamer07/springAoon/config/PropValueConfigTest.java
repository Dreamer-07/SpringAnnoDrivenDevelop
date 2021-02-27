package pers.dreamer07.springAoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import pers.dreamer07.springAoon.bean.Person;

/**
 * @author EMTKnight
 * @create 2021-02-27
 */


@SpringBootTest
public class PropValueConfigTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(PropValueConfig.class);


    @Test
    public void printBeans(){
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    /**
     * 测试 @Value
     */
    @Test
    public void test01(){
        printBeans();
        Person person = context.getBean("person", Person.class);
        System.out.println("=======>" + person);
    }


}
