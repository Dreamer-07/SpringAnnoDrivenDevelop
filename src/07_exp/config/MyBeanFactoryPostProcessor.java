package pers.dreamer07.springAoon.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

/**
 * 自定义 BeanFactoryPostProcessor 类型的实现类
 *      1) 和 BeanPostProcessor 的不同: 是在 beanFactory 实例初始化工作之后执行的，此时容器中只有其他 bean 实例的定义信息对象，而没有对应的 bean 实例
 *      2) 执行时机:
 *         1) 创建容器时，通过 refresh() -> invokeBeanFactoryPostProcessors() 执行 beanFactory 初始化之后的工作
 *         2) 通过 beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false) 获取所有实现了 BeanFactoryPostProcessor 接口的 bean id
 * 		3) 和 PostProcessor 类似，根据实现的接口(PriorityOrdered,Ordered,Other)进行分类
 * 			  创建对应三个 List 容器用来保存对应的组件
 * 		4) 创建对应的 bean 实例添加到对应的容器后进行排序
 * 	    5) 遍历容器中的 beanFactoryPostProcessor ,执行对应的 postProcessBeanFactory() 方法完成 beanFactory 初始化之后的工作
 * 		*) 以上工作都在初始化其他组件之前执行
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-05
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("当前容器中 bean 的个数： " + beanFactory.getBeanDefinitionCount());
        System.out.println("当前容器中的 bean id：" + Arrays.asList(beanFactory.getBeanDefinitionNames()));
    }

}
