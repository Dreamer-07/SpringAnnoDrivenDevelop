package pers.dreamer07.springAoon.bean;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 使用 Spring 底层的组件 - 实现 xxxAware 接口
 * @author EMTKnight
 * @create 2021-03-01
 */
@ToString
@Component
/*
* ApplicationContextAware：获取 IOC 容器
* BeanNameAware：获取当前 bean 实例使用的 id
* EmbeddedValueResolverAware：Spring 底层使用的占位符解析器(#{}，${}等)
* */
@Getter
public class Archer implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext context;
    private String beanName;
    private String strVal;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.strVal = resolver.resolveStringValue("当前操作系统是 ${os.name},计算 30 - 5 为 #{30-5}");
    }
}
