package pers.dreamer07.springAoon.event;

import org.springframework.context.ApplicationEvent;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-08
 **/
public class MyApplicationEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }

}
