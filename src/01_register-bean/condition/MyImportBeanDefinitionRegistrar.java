package pers.dreamer07.springAoon.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import pers.dreamer07.springAoon.bean.Person;

/**
 * @author EMTKnight
 * @create 2021-02-26
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 手动设计处理逻辑并注册对应的 bean
     * @param importingClassMetadata 使用 @Import 注解的类的注解信息
     * @param registry 管理 bean 注册的对象
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // containsBeanDefinition("beanId"): 判断注册的 bean 中是否有对应 id 的 bean 实例，有返回 true，反之相反
        boolean isContains1 = registry.containsBeanDefinition("person01");
        boolean isContains2 = registry.containsBeanDefinition("person03");
        // 如果存在相应的 bean 实例就手动注册一个 bean
        if(isContains1 && isContains2){
            // RootBeanDefinition 是 BeanDefinition 的实现类(可以指定 bean 的作用域等信息)
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Person.class);
            // registerBeanDefinition("beanId", BeanDefinition beanDefinition(bean 实例描述对象))：注册一个 bean 实例，并指定 id 和对应的 bean 实例描述对象
            registry.registerBeanDefinition("巴御前",beanDefinition);
        }
    }
}
