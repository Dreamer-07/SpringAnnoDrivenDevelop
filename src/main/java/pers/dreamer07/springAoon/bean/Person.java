package pers.dreamer07.springAoon.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author EMTKnight
 * @create 2021-02-27
 */
@Data
public class Person {

    /**
     * 使用 @Value 进行赋值
     *  作用在属性上
     *  value 可选值
     *       1. 基本数据
     *       2. 使用 SpEL(#{}) 完成某些运算
     *       3. 使用 ${} 读取运行环境的值
     */
    @Value("巴御前")
    private String name;

    @Value("#{20 - 2}")
    private Integer age;

    @Value("${person.nickName}")
    private String nickName;

}
