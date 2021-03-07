package pers.dreamer07.springAoon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.dreamer07.springAoon.dao.BookDAO;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-04
 **/
@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    @Transactional
    public void insertBook(){
        bookDAO.addBook();
        System.out.println("插入完成");
//        int i = 10 / 0;
    }
}
