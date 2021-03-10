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
public class Master {


    private Servant servant;

    @Autowired
    public void setServant(Servant servant){
        this.servant = servant;
    }

}
