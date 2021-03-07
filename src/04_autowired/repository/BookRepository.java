package pers.dreamer07.springAoon.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author EMTKnight
 * @create 2021-02-27
 */
@Repository
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Primary // 作为 IOC 容器中自动装配的首选项
public class BookRepository {

    private Integer id = 1;

}
