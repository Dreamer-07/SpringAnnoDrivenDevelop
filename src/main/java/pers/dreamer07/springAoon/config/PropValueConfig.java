package pers.dreamer07.springAoon.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import pers.dreamer07.springAoon.bean.Person;

/**
 * 属性赋值
 * @author EMTKnight
 * @create 2021-02-27
 */
@Configurable
/*
  @PropertySource: 读取指定的配置文件的内容(k/v)并保存到运行的环境变量中
 */
@PropertySource({"classpath:/person.properties"})
public class PropValueConfig {

    @Bean
    public Person person(){
        return new Person();
    }

}
