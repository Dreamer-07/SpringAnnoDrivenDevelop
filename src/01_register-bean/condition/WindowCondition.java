package pers.dreamer07.springAoon.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 如果该类需要作为一个判断条件，那么就需要实现 Condition 接口
 * @author EMTKnight
 * @create 2021-02-25
 */
public class WindowCondition implements Condition {

    /**
     * 判断条件的主执行方法
     * @param context 判断条件时的上下文环境
     * @param metadata 注释信息
     * @return 返回 true 代表条件满足，反之相反
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        /* ConditionContext context 方法
        *   getBeanFactory(); 可以获取 ioc 容器使用的 bean Factory
        *   getClassLoader(); 可以获取类加载器
        *   getEnvironment(); 可以获取运行时的环境
        *   getRegistry(); 可以获取 bean 定义的注册类(可以实现对注册 bean 的增删查改)
        * */
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        return osName != null && osName.contains("Windows");
    }
}
