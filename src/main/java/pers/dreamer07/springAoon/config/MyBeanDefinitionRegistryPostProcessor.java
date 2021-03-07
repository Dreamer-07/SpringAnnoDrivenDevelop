package pers.dreamer07.springAoon.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * BeanDefinitionRegistryPostProcessor：
 *  作用： 修改 bean 定义信息
 *  执行时机： 在 bean 定义信息即将被加载时，且 bean 实例还未被创建
 *  原理
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
        /* 创建 bean 定义信息对象的方式
        *   1. 通过对应的
        *  */
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
    }

    /**
     * 作为 BeanFactoryPostProcessor 接口实现类的实现的方法(bean 信息加载完成)
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
