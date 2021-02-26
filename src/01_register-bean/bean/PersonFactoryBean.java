package pers.dreamer07.springAoon.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 通过实现 Factory 用于控制 Person bean 的实例化
 * @author EMTKnight
 * @create 2021-02-26
 */
public class PersonFactoryBean implements FactoryBean<Person> {

    /**
     * 该方法的返回结果会作为 bean 实例装配到 IOC 容器中
     * @return
     * @throws Exception
     */
    @Override
    public Person getObject() throws Exception {
        return new Person("巴御前",17);
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
