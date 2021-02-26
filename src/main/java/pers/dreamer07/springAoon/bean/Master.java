package pers.dreamer07.springAoon.bean;

import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 管理 bean 生命周期的初始化和销毁方式3
 *      根据 JSR250 使用以下注解
 *          1. @PostConstruct：作用在方法上，在对应的 bean 实例创建且属性赋值完成后，调用该方法
 *          2. @PreDestroy：作用在方法上，在对应的 bean 实例即将销毁之前调用该方法
 * @author EMTKnight
 * @create 2021-02-26
 */
@ToString
public class Master {

    public Master() {
        System.out.println("Master()..");
    }

    @PostConstruct
    public void init(){
        System.out.println("master..init....");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("master...destroy");
    }
}
