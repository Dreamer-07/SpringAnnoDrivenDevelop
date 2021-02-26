package pers.dreamer07.springAoon.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import pers.dreamer07.springAoon.bean.Person;

import java.util.Map;

/**
 * @author EMTKnight
 * @create 2021-02-24
 */
@SpringBootTest
@Slf4j
public class MainConfigTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

    /**
     * 通过 AnnotationConfigApplicationContext 获取配置类中的 Bean 实例
     */
    @Test
    public void test01(){
        // 1. 调用 AnnotationConfigApplicationContext 的构造函数，创建配置类，创建 ApplicationContext 类对象
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        // 2. 方法调用
        // 2.1 通过 getBean(Class) 传入 Class 类型获取对应的 bean 实例
        Person person = applicationContext.getBean(Person.class);
        // 2.2 通过 getBeanNamesForType(Class) 传入 Class 类型获取 IOC 容器中所有对应的 bean 实例的 id
        String[] names = applicationContext.getBeanNamesForType(Person.class);

        // 3. 输出测试
        log.info(person.toString()); // Person(name=巴御前, age=16)
        for (String name : names) {
            System.out.println(name); // person
        }
    }

    /**
     * 测试 @ComponentScan 注解实现组件扫描
     */
    @Test
    public void test02(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        // 通过 getBeanDefinitionNames() 获取 IOC 容器中所有 bean 实例的 id
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {

            System.out.println(name);
        }
    }

    /**
     * 测试 @Scope 注解实现 bean 实例的作用域
     */
    @Test
    public void test03(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        // 如果是 scope.value = singleton,那么 person 实例的创建将快于 62 行输出
        System.out.println("ioc 容器创建完成");
        Object person = context.getBean("person01");
        Object person2 = context.getBean("person01");
        System.out.println(person == person2); // singleton：true; prototype: false
    }

    /**
     * 测试 @Conditional 注解按照条件注册 bean 实例
     */
    @Test
    public void test04(){
        // getBeansOfType(Class)：可以获取容器中指定类型的 bean 实例和 id 组成的 Map
        Map<String, Person> personMap = context.getBeansOfType(Person.class);

        // getEnvironment()：获取当前容器运行的环境信息对象(Environment)
        Environment environment = context.getEnvironment();
        // environment.getProperty("key")：根据 key 获取对应的环境信息
        String osName = environment.getProperty("os.name"); // os,name -> 运行时的操作系统
        System.out.println(osName);

        for (String name : personMap.keySet()) {
            System.out.println(name + " ---> " + personMap.get(name));
        }
    }

    /**
     * 测试 @Import 注解给容器中快速导入组件
     */
    @Test
    public void test05(){
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            // 通过 @Import 导入的组件 id 为对应的全类名
            System.out.println(name);
        }
    }

    /**
     * 测试 FactoryBean 注册的 bean 实例
     */
    @Test
    public void test06(){
        Person person = context.getBean("personFactoryBean", Person.class);
        // 通过注册 FactoryBean 实例时使用的 id 得到的是其通过 getObject() 方法返回的 bean 实例
        System.out.println(person); // Person(name=巴御前, age=17)
        // 如果需要获取的是 FactoryBean 实例，可以在 id 前面加上 &
        Object bean = context.getBean("&personFactoryBean");
        System.out.println(bean); // pers.dreamer07.springAoon.bean.PersonFactoryBean@1532c619
    }
}
