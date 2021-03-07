package pers.dreamer07.springAoon.bean;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author EMTKnight
 * @create 2021-02-28
 */
@Component
@NoArgsConstructor
public class Master implements ApplicationContextAware {

    private Servant servant;
    private ApplicationContext context;

    /**
     * 将 @Autowired 作用在构造器上，IOC 在创建对应的 bean 实例对象时就会调用相应的构造器
     * 其中使用的自定义类型的参数，会通过 IOC 容器获取
     * @param servant
     */
    @Autowired
    public Master(Servant servant) {
        System.out.println("Master(Servant)....");
        this.servant = servant;
    }

    /**
     * 将 @Autowired 作用在方法上，当对象作为 IOC 容器的 bean 实例创建时
     * 会自动调用该方法，其中自定义类型的参数会从 IOC 容器中获取
     * @param servant
     */
    // @Autowired
    public void setServant(Servant servant) {
        this.servant = servant;
    }

    public Servant getServant() {
        return servant;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}
