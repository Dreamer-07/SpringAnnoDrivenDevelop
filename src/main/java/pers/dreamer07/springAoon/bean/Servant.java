package pers.dreamer07.springAoon.bean;

import lombok.ToString;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 管理 bean 生命周期的初始化和销毁方式2
 *      - 实现 InitializingBean 接口，实现初始化方法的具体逻辑 afterPropertiesSet()
 *      - 实现 DisposableBean 接口，实现销毁方法的具体逻辑 destroy()
 * @author EMTKnight
 * @create 2021-02-26
 */
@Component
@ToString
public class Servant implements InitializingBean, DisposableBean {

    public Servant() {
        System.out.println("Servant()...");
    }

    /**
     * 初始化方法：会在创建 bean 实例，并完成属性赋值后执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("servant,,afterPropertiesSet()...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("servant,,destroy()...");
    }
}
