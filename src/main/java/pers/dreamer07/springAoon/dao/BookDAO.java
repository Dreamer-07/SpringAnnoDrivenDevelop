package pers.dreamer07.springAoon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-04
 **/
@Repository
public class BookDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addBook(){
        String sql = "INSERT INTO student(`username`, age) VALUES(?,?)";
        String useranme = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        jdbcTemplate.update(sql, useranme, 10);
    }

}
