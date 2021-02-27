package pers.dreamer07.springAoon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author EMTKnight
 * @create 2021-02-26
 */
@Data
@NoArgsConstructor
@ToString
public class Person {

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        System.out.println("Person()....");
        this.name = name;
        this.age = age;
    };

    // 对应的 bean 实例初始化时调用的方法
    public void init(){
        System.out.println("person..init()...");
    }

    // 对应的 bean 实例销毁时调用的方法
    public void destroy(){
        System.out.println("person..destroy()...");
    }
}
