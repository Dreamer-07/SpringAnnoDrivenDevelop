package pers.dreamer07.springAoon.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.dreamer07.springAoon.repository.BookRepository;

/**
 * @author EMTKnight
 * @create 2021-02-27
 */
@Service
@ToString
public class BookService {

    // 使用 @Autowired 完成对 IOC 中对应的组件的依赖注入
    @Autowired
    // @Qualifier("bookRepository2")
    private BookRepository bookRepository2;

    public BookRepository getBookRepository(){
        return bookRepository2;
    }

}
