package pers.dreamer07.springAoon.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 实现 ApplicationListener 接口监听容器发布的事件
 * @author EMTKnight
 * @create 2021-03-07
 */
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 每当有对应的监听事件(这里是 ApplicationEvent 及其实现类)发布时，就会触发该方法
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("发布事件：" + event);
    }


}
