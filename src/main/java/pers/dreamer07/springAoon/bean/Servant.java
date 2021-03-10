package pers.dreamer07.springAoon.bean;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-10
 **/
@Component
@ToString
public class Servant {

    private Master master;

    @Autowired
    public void setMaster(Master master){
        this.master = master;
    }

}
