package pers.dreamer07.springAoon.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import pers.dreamer07.springAoon.SpringAoonApplication;
import pers.dreamer07.springAoon.bean.Person;
import pers.dreamer07.springAoon.bean.PersonFactoryBean;
import pers.dreamer07.springAoon.condition.LinuxCondition;
import pers.dreamer07.springAoon.condition.MyImportBeanDefinitionRegistrar;
import pers.dreamer07.springAoon.condition.MyImportSelector;
import pers.dreamer07.springAoon.condition.WindowCondition;
import pers.dreamer07.springAoon.filter.MyTypeFilter;

/**
 * @author EMTKnight
 * @create 2021-02-24
 */
// @Configuration：告诉 Spring 这是一个配置类(等同于配置文件)
@Configuration
/* @ComponentScan：组件扫描
*   value：扫描指定包下带有 @Controller、@Service、@Repository、@Component
*          注解的组件并注册对应的 bean 实例，id 默认为类名(首字母小写)
*   excludeFilters: 指定一个或多个 @Filter，过滤指定的组件
*       @Filter：配置过滤条件
*           type：过滤规则，值为 FilterType 枚举类实例
*           classes：对应的实例类型
*   includeFilters：指定一个/多个 @Filter，只包含指定改的组件
*       @Filter 和上面一样
*       还需要设置当前 @ComponentScan 注解的 useDefaultFilters 属性为 false(关闭默认的过滤规则)
*   useDefaultFilters：是否使用默认的过滤规则，默认为 true
* */
@ComponentScan(basePackages = "pers.dreamer07.springAoon",
        // 排除指定的组件
        excludeFilters = {
        // 只包含指定的组件, 还需要设置 useDefaultFilters 属性为 false(关闭默认的过滤规则)
//        useDefaultFilters = false,
//        includeFilters = {
           @Filter(type = FilterType.ANNOTATION, classes = Controller.class),
           @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SpringAoonApplication.class),
            // 使用自定义的过滤规则
//            @Filter(type=FilterType.CUSTOM, classes = {MyTypeFilter.class})
        }
)
// @Conditional: (作用在类上) 只有满足相应的条件，这个类中的所有 bean 注册才会生效
// @Conditional
/* @Import: 快速给容器中导入一个/多个组件
    使用：
        1. 直接传入对应的组件的 Class
        2. 传入一个 ImportSelector 接口的实现类，该接口的抽象方法 selectImports() 需要返回需要导入组件的全类名构成的字符串数组
        3. 传入一个 ImportBeanDefinitionRegistrar 接口的实现类，可以手动注册定义逻辑注册对应的 bean 实例
* */
@Import({Person.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig {

    /**
     * @Bean: 创建一个 bean 实例；
        类型为对应的返回值类型；id 默认为方法名
        可以指定注解的 value 值为对应的 id

     * @Scope: 配置 bean 实例的作用域
        value 可取值
            ConfigurableBeanFactory#SCOPE_PROTOTYPE -> singleton
            ConfigurableBeanFactory#SCOPE_SINGLETON -> prototype
            org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST -> request
            org.springframework.web.context.WebApplicationContext#SCOPE_SESSION -> session
        说明：
            singleton：单实例(默认值)，在 IOC 容器创建时创建
            prototype：多实例，在获取对应的 bean 实例时创建
            request(不常用)：在同一次请求内创建一次
            session(不常用)：在同一次会话内创建一次

     * @Lazy: 懒加载 - 针对于单实例使用，在第一次获取 bean 实例时创建
     *
     * @return
     */
    @Bean("person01")
    @Scope("singleton")
//    @Lazy
    public Person person(){
        System.out.println("person 实例创建");
        return new Person("巴御前",16);
    }

    /**
     * @Conditional: (作用在方法上) 只有满足相应的条件，才会注册对应的 bean 实例
     * @return
     */
    @Conditional({LinuxCondition.class})
    @Bean
    public Person person02(){
      return new Person("张三", 16);
    };

    @Conditional(WindowCondition.class)
    @Bean
    public Person person03(){
        return new Person("李四", 20);
    }

    /**
     * 注册 FactoryBean
     * @return
     */
    @Bean
    public PersonFactoryBean personFactoryBean(){
        return new PersonFactoryBean();
    }

}
