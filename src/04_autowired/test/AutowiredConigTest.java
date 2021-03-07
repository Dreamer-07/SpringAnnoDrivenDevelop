package pers.dreamer07.springAoon.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.dreamer07.springAoon.bean.Archer;
import pers.dreamer07.springAoon.bean.Master;
import pers.dreamer07.springAoon.bean.Servant;
import pers.dreamer07.springAoon.controller.BookController;
import pers.dreamer07.springAoon.repository.BookRepository;
import pers.dreamer07.springAoon.service.BookService;

/**
 * @author EMTKnight
 * @create 2021-02-27
 */
@SpringBootTest
public class AutowiredConigTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AutowiredConfig.class);

    /**
     * 测试 @Autowired
     */
    @Test
    public void test01(){

        BookRepository bookRepository = context.getBean("bookRepository", BookRepository.class);
        BookService bookService = context.getBean(BookService.class);
        System.out.println(bookRepository);
        System.out.println(bookService.getBookRepository());
        System.out.println(bookRepository == bookService.getBookRepository());
    }

    /**
     * 测试 @Resource & @Inject
     */
    @Test
    public void test02(){
        BookService bookService = context.getBean("bookService2", BookService.class);
        BookController bookController = context.getBean(BookController.class);
        System.out.println(bookService == bookController.getBookService()); // false
    }

    /**
     * 测试 @Autowired 作用在[方法、构造器]上
     */
    @Test
    public void test03(){
        Master master = context.getBean(Master.class);
        Servant servant = context.getBean(Servant.class);
        System.out.println(master.getServant() == servant); // true
    }

    /**
     * 测试通过实现 xxxAware 接口获取 Spring 底层使用的组件
     */
    @Test
    public void test04(){
        Archer archer = context.getBean(Archer.class);
        System.out.println(archer.getContext() == context); // true
        System.out.println(archer.getBeanName()); // archer
        System.out.println(archer.getStrVal()); // 当前操作系统是 Windows 10,计算 30 - 5 为 25
    }
}
