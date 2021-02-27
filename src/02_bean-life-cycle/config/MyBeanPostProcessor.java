package pers.dreamer07.springAoon.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 管理 bean 生命周期的初始化和销毁方式4
 *      实现 BeanPostProcessor 接口，自定义 bean 的后置处理器
 *      重写两个方法：
 *          1. postProcessBeforeInitialization：在所有初始化方法之前执行
 *          2. postProcessAfterInitialization：在所有初始化方法执行之后执行
 * @author EMTKnight
 * @create 2021-02-26
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在 bean 实例的所有初始化方法执行之前
     * @param bean 当前的操作的 bean 实例
     * @param beanName 对应的 bean id
     * @return 处理后的 bean 实例 / null(不进行任何处理)
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + ".postProcessBeforeInitialization() => " + bean);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + ".postProcessAfterInitialization() => " + bean);
        return null;
    }
}
