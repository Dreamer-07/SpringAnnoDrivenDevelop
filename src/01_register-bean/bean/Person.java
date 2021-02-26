package pers.dreamer07.springAoon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author EMTKnight
 * @create 2021-02-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    private String name;
    private Integer age;

}
