package pers.dreamer07.springAoon.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pers.dreamer07.springAoon.bean.Person;

/**
 * BeanDefinitionRegistryPostProcessor：
 *  作用： 修改 bean 定义信息
 *  执行时机： 在 bean 定义信息即将被加载时，且 bean 实例还未被创建
 *  原理：
 *      1) 启动容器 -> refresh() -> invokeBeanFactoryPostProcessors()
 *      2) 通过 beanFactory.getBeanNamesForType() 获取容器中的所有 BeanDefinitionRegistryPostProcessor
 *      3) 根据实现的不同的接口(PriorityOrdered, Order, other) 添加到对应的 List 容器中
 *      4) 进行排序和对应的 bean 实例注册
 *      5) 通过 invokeBeanDefinitionRegistryPostProcessors() 执行所有
 *         BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry() 方法
 *      6) 执行对应的 BeanDefinitionRegistryPostProcessor.postProcessBeanFactory()
 *      7) 执行其他 BeanFactoryPostProcessor.postProcessorBeanFactory() 方法`
 *  注意：BeanDefinitionRegistryPostProcessor 的执行早于 BeanFactoryPostProcessor
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-07
 **/

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 作为 BeanDefinitionRegistryPostProcessor 接口实现类需要实现的方法(bean 定义信息即将被加载)
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry() 当前 bean 定义信息个数："  + registry.getBeanDefinitionCount());
        /* 创建 bean 定义信息对象的方式
        *   1. 创建 RootBeanDefinition 对象，定义 bean 的信息
        *   2. 通过 BeanDefinitionBuilder 的静态方法构建一个 beanDefinition 对象
        *  */
        RootBeanDefinition beanDefinition = new RootBeanDefinition(Person.class);
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
        // 通过 BeanDefinitionRegistry 类实例注册对应的 bean 定义信息
        registry.registerBeanDefinition("person01", beanDefinition);
    }

    /**
     * 作为 BeanFactoryPostProcessor 接口实现类的实现的方法(bean 信息加载完成)
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory() 当前 bean 定义信息个数：" + beanFactory.getBeanDefinitionCount());
    }
}
