package pers.dreamer07.springAoon.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import pers.dreamer07.springAoon.bean.Master;
import pers.dreamer07.springAoon.bean.Person;

/**
 * 指定 bean 生命周期中初始化和销毁时调用的方法
 * @author EMTKnight
 * @create 2021-02-26
 */
@Configurable
@ComponentScan("pers.dreamer07.springAoon.bean")
@Import({Master.class,MyBeanPostProcessor.class})
public class LifeCycleConfig {

    /**
     * 1. 指定 @Bean 注解的 initMethod 和 destroyMethod 为对应的 bean 实例中的方法
     * @return
     */
    @Bean(initMethod="init", destroyMethod = "destroy")
    public Person person(){
        return new Person("巴御前", 16);
    }

}
