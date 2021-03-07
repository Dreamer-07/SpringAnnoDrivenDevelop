package pers.dreamer07.springAoon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-04
 **/
@SpringBootTest
public class bookServiceTest {

    @Autowired
    public BookService bookService;

    @Test
    public void test01(){
        bookService.insertBook();
    }
}
