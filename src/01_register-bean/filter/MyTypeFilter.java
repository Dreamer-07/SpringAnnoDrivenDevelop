package pers.dreamer07.springAoon.filter;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Objects;


/**
 * 自定义过滤规则
 * @author EMTKnight
 * @create 2021-02-24
 */
public class MyTypeFilter implements TypeFilter {

    /**
     * 过滤时调用的方法，
     * @param metadataReader 当前正在扫描的类
     * @param metadataReaderFactory 工厂类，可以获取其他类
     * @return 根据 (exclude / include) 不同而不同，返回 true 表示(不需要/需要)，false 表示(需要/不需要)
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        /* metadataReader
        * 获取当前类使用的注解：metadataReader.getAnnotationMetadata();
        * 获取当前类的信息(父类，实现接口等)：metadataReader.getClassMetadata();
        * 获取当前类的资源(类的路径等)：metadataReader.getResource();
        * */
        System.out.println("当前类类名：" + metadataReader.getClassMetadata().getClassName());
        // 过滤规则：判断当前类的父类名是否包含 controller
        if(Objects.requireNonNull(metadataReader.getClassMetadata().getSuperClassName()).contains("Object") ){
            System.out.println("当前类父类名：" + metadataReader.getClassMetadata().getSuperClassName());
            return true;
        }
        return false;
    }

}
