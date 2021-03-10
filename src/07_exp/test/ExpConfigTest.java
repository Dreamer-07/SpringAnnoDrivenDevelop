package pers.dreamer07.springAoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.dreamer07.springAoon.event.MyApplicationEvent;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-05
 **/
@SpringBootTest
public class ExpConfigTest {

    @Test
    public void test01(){
        // MyBeanFactoryPostProcessor.postProcessBeanFactory() 方法执行早于 Person 组件的创建
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExpConfig.class);

        // 通过 context.publishEvent 容器发布事件
//        context.publishEvent(new ApplicationEvent(new String("发布事件")) {
//            @Override
//            public Object getSource() {
//                return super.getSource();
//            }
//        });
        // 通过 context.publishEvent 发布自定义事件
        context.publishEvent(new MyApplicationEvent("自定义事件发布"));

        // 关闭容器
        context.close();
    }

}
