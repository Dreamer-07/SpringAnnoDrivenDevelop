package pers.dreamer07.springAoon.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pers.dreamer07.springAoon.event.MyApplicationEvent;

/**
 * 测试 @EventListener
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-08
 **/
@Service
public class ServantService {

    /**
     * @EventListener: 标识该方法为一个监听器
     *      classes: 属性指定为要监听的事件类型
     * @param event: 对应的事件对象会通过参数的形式注册进来
     */
    @EventListener(classes = {MyApplicationEvent.class})
    public void listener(ApplicationEvent event){
        System.out.println("监听到自定义事件：" + event);
    }

}
