# Spring ADD(注解驱动开发)

> 基于 Spring4.3.12

# 第一章 容器

## **1.1 组件注册**

> 源码：src/01_register-bean
>
> ​	- test 目录下为对应的测试代码

### 一、注解开发

#### 1) @Configuration

- 概念：告诉 Spring 这是一个配置类(配置文件)

#### 2) @Bean

- 概念：在容器中配置一个 bean 实例

- 使用
  - 类型为返回值类型，id 默认为方法名
  - 可以指定**注解的 value 值** 为对应的 id 
- 标注的方法中使用的参数都会从 IOC 容器中获取
  
- 实例

  ```java
  @Bean
  public Person person(){...};
  ```

#### 3) @ComponentScan 

- 概念：开启组件扫描

- 使用

  - value：扫描指定包下带有 @Controller、@Service、@Repository、@Component

    注解的组件并注册对应的 bean 实例，id 默认为类名(首字母小写)

  - excludeFilters: 指定一个或多个 `@Filter`，过滤指定的组件

    - `@Filter`：配置过滤条件

      - type：过滤规则，值为 FilterType 枚举类实例

        | ANNOTATION      | 根据注解来排除                                               |
        | --------------- | ------------------------------------------------------------ |
        | ASSIGNABLE_TYPE | 根据类类型来排除                                             |
        | ASPECTJ         | 根据AspectJ表达式来排除                                      |
        | REGEX           | 根据正则表达式来排除                                         |
        | CUSTOM          | 自定义FilterClass排除，需要实现`org.springframework.core.type.filter.TypeFilter`接口 |

      - classes：对应的实例类型

      - 实例

        ```java
        @ComponentScan(basePackages = "pers.dreamer07.springAoon", excludeFilters = {
            @Filter(type = FilterType.ANNOTATION, classes = Controller.class),
            @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SpringAoonApplication.class)
        })
        public class MainConfig
        ```

  - includeFilters：指定一个/多个 @Filter，只包含指定改的组件

    - `@Filter` 和上面的一样

    - 还需要设置当前 `@ComponentScan` 注解的 useDefaultFilters 属性为 false(关闭默认的过滤规则)

    - 实例

      ```java
      @ComponentScan(basePackages = "pers.dreamer07.springAoon",
      	// 排除指定的组件
          // 只包含指定的组件, 还需要设置 useDefaultFilters 属性为 false(关闭默认的过滤规则)
          useDefaultFilters = false,
          includeFilters = {
              @Filter(type = FilterType.ANNOTATION, classes = Controller.class),
              @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SpringAoonApplication.class)
          }
      )
      public class MainConfig 
      ```

  - useDefaultFilters：是否使用默认的过滤规则，默认为 true

- **注意**：

  1. 如果是 JDK8，则可以配置多个 `@ComponentScan`

     如果不是 JDK8，则可以使用 `@ComponentScans`, 在其中配置 value 值为多个 `@ComponentScan`

  2. 如果类 A 使用 excludeFilters ，在组件扫描时，如果发现类 B 的 `@ComponentScan` 注解**没有定义一样的排除规则**时，类 A，B 的排除规则都不会生效

     可以额外添加一个 `@Filter` 排除类 B 即可

- **拓展**

  1. 实现 TypeFilter 接口，自定义过滤规则

     ```java
     /**
      * 自定义过滤规则
      * @author EMTKnight
      * @create 2021-02-24
      */
     public class MyTypeFilter implements TypeFilter {
     
         /**
          * 过滤时调用的方法，
          * @param metadataReader 当前正在扫描的类
          * @param metadataReaderFactory 工厂类，可以获取其他类
          * @return 根据 (exclude / include) 不同而不同，返回 true 表示(不需要/需要)，false 表示(需要/不需要)
          * @throws IOException
          */
         @Override
         public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
             /* metadataReader
             * 获取当前类使用的注解：metadataReader.getAnnotationMetadata();
             * 获取当前类的信息(父类，实现接口等)：metadataReader.getClassMetadata();
             * 获取当前类的资源(类的路径等)：metadataReader.getResource();
             * */
             System.out.println("当前类类名：" + metadataReader.getClassMetadata().getClassName());
             // 过滤规则：判断当前类的父类名是否包含 controller
             if(Objects.requireNonNull(metadataReader.getClassMetadata().getSuperClassName()).contains("Object") ){
                 System.out.println("当前类父类名：" + metadataReader.getClassMetadata().getSuperClassName());
                 return true;
             }
             return false;
         }
     
     }
     ```

     使用

     ```java
     @ComponentScan(basePackages = "pers.dreamer07.springAoon",
             // 排除指定的组件
             excludeFilters = {
                 @Filter(type=FilterType.CUSTOM, classes = {MyTypeFilter.class})
             }
     )
     public class MainConfig
     ```

#### 4) @Scope

- 概念：调整组件(bean实例)的作用域

- 使用 
  - value 可取值
    1. singleton：单实例(默认值)，在 IOC 容器创建时创建
    2. prototype：多实例，在获取对应的 bean 实例时创建
    3. request(不常用)：在同一次请求内创建一次
    4. session(不常用)：在同一次会话内创建一次

- 实例

  ```java
  @Bean("person01")
  @Scope("prototype")
  public Person person(){
      System.out.println("person 实例创建");
      return new Person("巴御前",16);
  }
  ...
  // 测试代码
  @Test
  public void test03(){
      ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
      // 如果是 scope.value = singleton,那么 person 实例的创建将快于 62 行输出
      System.out.println("ioc 容器创建完成");
      Object person = context.getBean("person01");
      Object person2 = context.getBean("person01");
      System.out.println(person == person2); // singleton：true; prototype: false
  }
  ```

#### 5) @Lazy 

- 概念：懒加载 - 针对于单实例使用，在第一次获取 bean 实例时创建对应的 bean 实例

#### 6) @Condtional

- 作用范围

  1. 类上：只有满足相应的条件，这个类中的所有 bean 注册才会生效
  2. 方法上：只有满足相应的条件，才会注册对应的 bean 实例

- 使用

  - value 值：接收一个/多个实现了 **Condition** 接口的实现类
  - **Condition** 接口：条件接口，含有抽象方法 matches()，该方法如果返回 true 代表条件满足，反之相反

- **实例**

  1. 根据需求，实现 Condition 接口，完成对应的 matches() 方法的逻辑设计

     ```java
     // WindowCondition 实现类，判断运行环境是否为 windows
     public class WindowCondition implements Condition {
     
         /**
          * 判断条件的主执行方法
          * @param context 判断条件时的上下文环境
          * @param metadata 注解信息
          * @return 返回 true 代表条件满足，反之相反
          */
         @Override
         public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
             /* ConditionContext context 方法：
             *   getBeanFactory(); 可以获取 ioc 容器使用的 bean Factory
             *   getClassLoader(); 可以获取类加载器
             *   getEnvironment(); 可以获取运行时的环境
             *   getRegistry(); 可以获取 bean 定义的注册类(可以实现对注册 bean 的增删查改)
             * */
             Environment environment = context.getEnvironment();
             String osName = environment.getProperty("os.name");
             return osName != null && osName.contains("Windows");
         }
     }
     
     ...
         
     // LinuxCondition 实现类：判断运行环境是否为 linux
     public class WindowCondition implements Condition {
     
         @Override
         public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
             ...
             return osName != null && osName.contains("linux");
         }
     }
     ```

  2. 在注册 bean 的方法/类上的 `@Conditional` 注解中使用

     ```java
     // 方法上
     @Conditional({LinuxCondition.class})
     @Bean
     public Person person02(){
         return new Person("张三", 16);
     };
     
     @Conditional(WindowCondition.class)
     @Bean
     public Person person03(){
         return new Person("李四", 20);
     }
     
     // 类上
     @Conditional(WindowCondtion.class)
     public class MainConfig{}
     ```

  3. 测试

     ```java
     @Test
     public void test04(){
         // getBeansOfType(Class)：可以获取容器中指定类型的 bean 实例和 id 组成的 Map
         Map<String, Person> personMap = context.getBeansOfType(Person.class);
     
         // getEnvironment()：获取当前容器运行的环境信息对象(Environment)
         Environment environment = context.getEnvironment();
         // environment.getProperty("key")：根据 key 获取对应的环境信息
         String osName = environment.getProperty("os.name"); // os,name -> 运行时的操作系统
         System.out.println(osName); 
     
         for (String name : personMap.keySet()) {
             System.out.println(name + " ---> " + personMap.get(name));
         }
     }
     ```

     ![image-20210226091732968](README.assets/image-20210226091732968.png)

- 扩展

  - 修改 IDEA 的运行参数：https://blog.csdn.net/oz965557340/article/details/78165693

#### 7) @Import 

- 概念：快速导入组件

- 使用(针对 value 值)

  1. 直接传入对应的组件的 Class
  2. 传入一个 **ImportSelector** 接口的实现类，该接口的抽象方法 selectImports() 需要返回需要导入组件的全类名构成的字符串数组
  3. 传入一个 **ImportBeanDefinitionRegistrar** 接口的实现类，可以手动注册定义逻辑注册对应的 bean 实例

- **注意**：使用 @Import 注解导入的组件，id 为对应的全类名

- 实例：

  1. 设计对应的实现类

     ```java
     // 设计实现 ImportSelector 接口的实现类
     public class MyImportSelector implements ImportSelector {
     
         /**
          *
          * @param importingClassMetadata 当前使用了 @Import 注解的组件的其他注解的信息
          * @return 返回需要导入的组件的全类名构成的字符串数组
          */
         @Override
         public String[] selectImports(AnnotationMetadata importingClassMetadata) {
             for (String type : importingClassMetadata.getAnnotationTypes()) {
                 System.out.println("其他注解信息:" + type);
             }
     
             return new String[]{
                 "pers.dreamer07.springAoon.bean.Servant",
                 "pers.dreamer07.springAoon.bean.Master"
             };
         }
     
     }
     
     ...
     
     // 设计实现 ImportBeanDefinitionRegistrar 接口的实现类
     public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
     
         /**
          * 手动设计处理逻辑并注册对应的 bean
          * @param importingClassMetadata 使用 @Import 注解的类的注解信息
          * @param registry 管理 bean 注册的对象
          */
         @Override
         public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
             // containsBeanDefinition("beanId"): 判断注册的 bean 中是否有对应 id 的 bean 实例，有返回 true，反之相反
             boolean isContains1 = registry.containsBeanDefinition("person01");
             boolean isContains2 = registry.containsBeanDefinition("person03");
             // 如果存在相应的 bean 实例就手动注册一个 bean
             if(isContains1 && isContains2){
                 // RootBeanDefinition 是 BeanDefinition 的实现类(可以指定 bean 的作用域等信息)
                 RootBeanDefinition beanDefinition = new RootBeanDefinition(Person.class);
                 // registerBeanDefinition("beanId", BeanDefinition beanDefinition(bean 实例描述对象))：注册一个 bean 实例，并指定 id 和对应的 bean 实例描述对象
                 registry.registerBeanDefinition("巴御前",beanDefinition);
             }
         }
     }
     ```

  2. 使用 `@Import` 注解导入组件

     ```java
     // @Import: 快速给容器中导入一个/多个组件
     @Import({Person.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
     ```

  3. 测试，打印 bean 实例 id 即可

### **二、扩展类**

#### 1) AnnotationConfigApplicationContext：

- 概念：ApplicationContext 接口的实现类，用于创建配置类的 IOC 容器对象

- 方法

  1. getBean(Class)：传入 Class 类型获取对应的 bean 实例
  2. getBeanNamesForType(Class)：传入 Class 类型获取 IOC 容器中所有对应的 bean 实例的 id
  3. getBeanDefinitionNames()：获取当前 IOC 容器对象中所有 bean 实例的 id
  4. getBeansForType(Class)：可以获取容器中指定类型(Class)的 bean 实例和 id 组成的 Map
  5. getEnvironment()：获取当前容器运行的环境信息对象(Environment)

- 实例

  ```java
  // 1. 调用 AnnotationConfigApplicationContext 的构造函数，创建配置类，创建 ApplicationContext 类对象
  ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
  
  // 2. 方法调用
  // 2.1 通过 getBean(Class) 传入 Class 类型获取对应的 bean 实例
  Person person = applicationContext.getBean(Person.class);
  // 2.2 通过 getBeanNamesForType(Class) 传入 Class 类型获取 IOC 容器中所有对应的 bean 实例的 id
  String[] names = applicationContext.getBeanNamesForType(Person.class);
  // 2.3 通过 getBeanDefinitionNames() 获取 IOC 容器中所有 bean 实例的 id
  String[] beanDefinitionNames = context.getBeanDefinitionNames();
  // 2.4 getBeansOfType(Class)：可以获取容器中指定类型的 bean 实例和 id 组成的 Map
  Map<String, Person> personMap = context.getBeansOfType(Person.class);
  // 2.5 getEnvironment()：获取当前容器运行的环境信息对象(Environment)
  Environment environment = context.getEnvironment();
  
  // 3. 输出测试
  log.info(person.toString()); // Person(name=巴御前, age=16)
  for (String name : names) {
      System.out.println(name); // person
  }
  ```

#### 2) Environment

- 概念：Spring IOC 容器的运行环境

- 方法：

  1. getProperty("key")：根据 key 获取对应的环境信息

- 实例

  ```java
  // getEnvironment()：获取当前容器运行的环境信息对象(Environment)
  Environment environment = context.getEnvironment();
  // environment.getProperty("key")：根据 key 获取对应的环境信息
  String osName = environment.getProperty("os.name"); // os,name -> 运行时的操作系统
  System.out.println(osName); // Windows 10
  ```

#### 3) FactoryBean

- 概念：接口，也是一个 Bean，用户可以通过实现该接口用于定制实例化 Bean 的逻辑

- 使用：

  1. getObject(): FactoryBean 会将该方法的返回结果作为 bean 实例装配到 IOC 容器中
  2. getObjectType(): 获取对应的 bean 实例的类型
  3. isSingleton(): 是否单例，返回 true 则是，反之相反

- 实例：

  1. 实现 FactoryBean 接口

     ```java
     /**
      * 通过实现 Factory 用于控制 Person bean 的实例化
      * @author EMTKnight
      * @create 2021-02-26
      */
     public class PersonFactoryBean implements FactoryBean<Person> {
     
         /**
          * 该方法的返回结果会作为 bean 实例装配到 IOC 容器中
          * @return
          * @throws Exception
          */
         @Override
         public Person getObject() throws Exception {
             return new Person("巴御前",17);
         }
     
         @Override
         public Class<?> getObjectType() {
             return Person.class;
         }
     
         @Override
         public boolean isSingleton() {
             return false;
         }
     }
     ```

  2. 使用 @Bean 配置 FactoryBean

  3. 测试

     ```java
     @Test
     public void test06(){
         Person person = context.getBean("personFactoryBean", Person.class);
         // 通过注册 FactoryBean 实例时使用的 id 得到的是其通过 getObject() 方法返回的 bean 实例
         System.out.println(person); // Person(name=巴御前, age=17)
         // 如果需要获取的是 FactoryBean 实例，可以在 id 前面加上 &
         Object bean = context.getBean("&personFactoryBean");
         System.out.println(bean); // pers.dreamer07.springAoon.bean.PersonFactoryBean@1532c619
     }
     ```

- 注意：

  1. 通过注册 FactoryBean 的 id 在 IOC 容器中默认获取的是其 getObject() 方法返回后装配的 bean 实例
  2. 如果需要获取 FactoryBean 实例，可以在对应的 id 前加上 **&** 即可


### 三、总结

- 给 Spring IOC 容器中注册组件的方式
  - 对应自定义的组件：包扫描+组件注解(@Controller/@Service/@Repository/@Component) - id 为对应的类名首字母小写
  - 对应第三方的组件：使用 @Bean 进行注册 - id 为对应的方法名
  - 快速给容器中导入一个 bean：使用 @Import 注解 - id为对应的全类名
  - 使用 Spring 提供的 FactoryBean(工厂 Bean) - id 为对应的方法名

## 1.2 生命周期

### 一、概念

- bean 的生命周期：创建 -> 初始化 -> 销毁
- 而 Spring 中由 IOC 容器辅助帮我们进行管理
- 我们也可以自定义初始化和销毁方法

### 二、自定义 bean 初始化和销毁方法

#### 1). 通过 @Bean 注解

- 说明：指定 `@Bean` 注解的 `initNethod` 和 `destroyMethod` 属性为对应的 bean 实例对象中的方法

- 实例

  ```java
  // 1. 设计类和方法
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
  
  ...
  
      // 2. 使用 @Bean 注册时设置初始化方法和销毁方法
      @Configurable
      public class LifeCycleConfig {
  
          /**
       * 1. 指定 @Bean 注解的 initMethod 和 destroyMethod 为对应的 bean 实例中的方法
       * @return
       */
          @Bean(initMethod="init", destroyMethod = "destroy")
          public Person person(){
              return new Person("巴御前", 16);
          }
  
      }
  ```

#### 2). Bean 类实现 InitializingBean & **DisposableBean** 接口

- 说明：实现 **InitializingBean** 接口，完成初始化方法的逻辑；实现 **DisposableBean** 接口，完成销毁方法的逻辑

- 实例

  ```java
  public class Servant implements InitializingBean, DisposableBean {
  
      public Servant() {
          System.out.println("Servant()...");
      }
  
      /**
       * 初始化方法：会在创建 bean 实例，并完成属性赋值后执行
       * @throws Exception
       */
      @Override
      public void afterPropertiesSet() throws Exception {
          System.out.println("servant,,afterPropertiesSet()...");
      }
  
      @Override
      public void destroy() throws Exception {
          System.out.println("servant,,destroy()...");
      }
  }
  ```

  随后注册到 IOC 容器中即可

#### 3). Bean 类实现 JSR250 规范 

- 说明：在 Bean 类中使用 JSR250 规范中的 `@PostConstruct` 和 `@PreDestroy` 注解

- 使用

  - `@PostConstruct`：作用在方法上，在对应的对象创建且属性赋值完成后，调用该方法
  - `@PreDestroy`：作用在方法上，在对应的对象即将销毁之前调用该方法

- 实例

  ```java
  public class Master {
  
      public Master() {
          System.out.println("Master()..");
      }
  
      @PostConstruct
      public void init(){
          System.out.println("master..init....");
      }
  
      @PreDestroy
      public void destroy(){
          System.out.println("master...destroy");
      }
  }
  ```

  随后注册到 IOC 容器中即可

#### 4). 配置类实现 **BeanPostProcessor** 接口

- 说明：BeanPostProcessor -  bean 的后置处理器

- 使用 - 重写的两个方法

  1. postProcessBeforeInitialization()：在所有初始化方法之前执行
  2. postProcessAfterInitialization()：在所有初始化方法执行之后执行

- 实例

  ```java
  public class MyBeanPostProcessor implements BeanPostProcessor {
  
      /**
       * 在 bean 实例的所有初始化方法执行之前
       * @param bean 当前的操作的 bean 实例
       * @param beanName 对应的 bean id
       * @return 处理后的 bean 实例 / null(不进行任何处理)
       * @throws BeansException
       */
      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
          System.out.println(beanName + ".postProcessBeforeInitialization() => " + bean);
          return bean;
      }
  
      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
          System.out.println(beanName + ".postProcessAfterInitialization() => " + bean);
          return bean;
      }
  }
  ```

  注册到对应的 IOC 容器中即可

#### 5). BeanPostProcessor 执行过程 (参考 Spring5.x)

1. 在 BeanFactory 创建对应的 bean 实例时，会执行 `populateBean()` 方法用于进行属性赋值

2. 调用 `initializeBean()` 方法开始 bean 的初始化

3. 在初始化之前执行 `applyBeanPostProcessorsBeforeInitialization()` 方法

   该方法会遍历 IOC 配置的所有 **BeanPostProcessor**，并执行对应的 `postProcessBeforeInitialization()` 方法

   **如果该方法的返回值为 null，就返回处理之前的 bean 实例对象；否则直到遍历结束**

4. 执行初始化方法 `invokeInitMethods()`

5. 在初始化之后执行 `applyBeanPostProcessorsAfterInitialization()` 方法

   和第三步同理

**Spring 对 BeanPostProcessor 的使用：bean 赋值，注入其他组件，@Autowired 自动装配，@Async 等都是通过不同的 BeanPostProcessor 实现类完成的**

### *. bean 生命周期的执行时机

1. 创建：
   - 单实例：在容器启动时创建
   - 多实例：从容器中获取时创建
2. 属性赋值
3. 执行 bean 后置处理器的 postProcessBeforeInitialization()
4. 初始化：在对象创建好之后，并完成赋值之后
5. 执行 bean 后置处理器的 postProcessAfterInitialization()
6. 销毁：容器关闭时

## 1.3 属性赋值

### 一、注解开发

#### 1) @Value

- 说明：作用在属性上，在初始化之前完成属性赋值

- 使用(这里针对 value 取值)

  1. 基本数据
  2. 使用 SpEL 表达式(#{})
  3. 使用 ${} 读取环境变量的值

- 实例

  ```java
  public class Person {
      
      @Value("巴御前")
      private String name;
  
      @Value("#{20 - 2}")
      private Integer age;
  
      @Value("${person.nickName}")
      private String nickName;
  
  }
  ```

#### 2) @PropertySource

- 说明：读取指定配置文件中的内容(k/v)并保存到运行的环境变量中

- 实例

  ```java
  @PropertySource({"classpath:/person.properties"})
  public class PropValueConfig {
  ```

  ```properties
  person.nickName="巴ちゃん"
  ```

- 注意

  1. 保存到环境变量的中，也可以通过 **Environment** 实例对象获取

  2. 和 `@Component` 注解相识，可以通过多个 `@PropertySource` 读取多个配置文件，

     也可以通过使用 `@PropertySources` 中配置多个 `@PropertySource` 读取多个配置文件

#### 3) @Autowired

- 作用：自动装配：自动注入容器中对应的 bean 实例

  **Spring 利用依赖注入(DI)，完成对 IOC 容器中各个组件的依赖关系赋值**

- 使用

  1. 如果容器中只有**一个对应类型的 bean 实例**，就使用其进行依赖注入 DI (context.getBean(Class))

   2. 如果容器中有**多个对应类型的 bean 实例**，就将**对应的属性名作为 id** 寻找

      注册时如果有多个重复 id 的 bean 实例，则会覆盖

  3. 默认情况下使用该注解的属性容器中**必须**有对应的 bean 实例，否则就会报错

     可以指定其 required 属性为 false，就不会报错

   4. 可以配合 `@Qualifier` 注解指定 value 值为对应的 bean id

  5. 可以在**注册的 bean 实例上**使用 `@Primary` 注解指定其为 IOC 装配时使用的首选项

#### 4) @Qualifier

- 作用：根据 id 在 IOC 容器中查找对应的 bean 实例

#### 5) @Primary

- 作用：将对应的注册 bean 实例作为 IOC 自动装配时使用的**首选项**

#### 6) @Resource(JSR 250规范)

- 作用：实现自动装配
- 使用
  - 默认通过属性名装配对应的 bean 实例，可以通过指定 name 属性值装配指定的 bean 实例
  - 不支持 `@Qualifier` 和 `@Primary` 
  - 无法使用和 `@Autowired(required = false)` 的功能

#### 7) @Inject(JSR 330规范)

- 作用：实现自动装配
- 使用(需要导入 **javax inject** 包)
  - 默认通过属性名装配对应的 bean 实例
  - 支持 `@Qualifier` 和 `@Primary` 
  - 无法指定任何属性

#### 8) @Autowired & @Resouce & @Inject 的区别

1. @Autowired 在 Spring 中使用的最为广泛，但只能在 Spring 中使用

   而后两者属于 Java 规范，可以在其他的 IOC 框架中使用

2. 强度：@Autowired > @Inject > @Resouce(在 Spring 中的使用)

3. 但三者在 Spring 中都是通过 **AutowiredAnnotationBeanPostProcessor** bean 后置处理器实现自动装配

#### 9) @Autowired 的其他使用

1. 作用在**方法**上

   - 使用：当对象作为 IOC 的 bean 实例创建时都会调用该方法，而方法中自定义类型的参数会从 IOC 容器中获取

   - 实例

     ```java
     @Autowired
     public void setServant(Servant servant) {
         this.servant = servant;
     }
     ```

     ```java
     @Test
     public void test03(){
         Master master = context.getBean(Master.class);
         Servant servant = context.getBean(Servant.class);
         System.out.println(master.getServant() == servant); // true
     }
     ```

   - 注意：如果是 `@Bean` 标注的方法，其中使用的参数都会从 IOC 容器中获取

2. 作用在**构造器**上

   - 使用：

     当对象作为 IOC 的 bean 实例创建时**默认都会调用无参构造器**，再进行初始化赋值操作

     如果 `@Autowired` 注解标注在对应的有参构造器上时，则会调用对应的构造器

     其中使用的自定义类型的参数，也是通过 IOC 容器获取的

   - 实例：

     ```java
     @Autowired
     public Master(Servant servant) {
         System.out.println("Master(Servant)....");
         this.servant = servant;
     }
     ```

   - 注意：

     1. 当类的构造器**有且只有一个构造器**时，无论使不使用 `@Autowired` 都会调用该构造器，其中自定义类型的参数，依然会通过 IOC 中获取
     2. 一个类中**不能有两个/两个以上**由 `@Autowired` 注解**标注的构造器**

3. 作用在**参数**上

   - 使用：

     效果和放在方法/构造器上一致；但在**构造器**上时，默认还是会调用无参构造器，除非只有当前一个构造器

     都会从 IOC 容器中获取对应的类型的组件完成赋值

#### 10) @Profile 

- 说明：Spring 为我们提供的**可以根据当前环境，动态的激活/切换一系列组件**的功能

- 例如：根据不同的环境(开发环境/测试环境/生产环境)切换数据源

- 使用：

  1. 可以通过指定 value 值为环境标识，来确定组件在对应的环境下才会被注册到容器中

  2. 使用**命令行动态参数**：在虚拟机参数位置输入 `-Dspring.profiles.acvtive=环境标识`

  3. Spring Boot 中根据命名规则(application-{profile}，profile=dev ：开发环境、test：测试环境、prod：生产环境)，

  4. 也可以在 `application.properties` 中使用 **spring.profiles.active** 项激活一个/多个配置文件

     ```properties
     spring.profiles.active: prod,proddb,prodmq
     ```

     如果没有指定就会默认启动application-default.properties。

  5. 如果将其**标注在类上**就代表只有在对应的运行环境下其中的所有配置才可以生效

- 注意：

  1. 如果不指定在任何环境下都不会注册组件
  2. 不使用该注解和指定该注解和 value 值为 **default** 一致

### 二、扩展

#### 1) 使用 Spring 底层组件

- 说明：如果自定义组件想要使用 Spring 容器底层组件(IOC 容器、BeanFactory等)，是需要实现对应的 `xxxAware` 接口

- **Aware** 接口：实现该接口的各种继承接口可以完成对应的需求

  (例如)

  1. 实现 **AplicationContextAware** 接口，通过 setApplicationContext() 方法**保存 IOC 容器**
  2. 实现 **BeanNameAware** 接口，通过 setBeanName() 方法获取 beanName
  3. 实现 **EmbeddedValueResolverAware** 接口，通过 setEmbeddedValueResolver() 方法使用 Spring 用的占位符解析器(#{}、${})

- 实例

  ```java
  @ToString
  @Component
  @Getter
  public class Archer implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
  
      private ApplicationContext context;
      private String beanName;
      private String strVal;
  
      @Override
      public void setApplicationContext(ApplicationContext context) throws BeansException {
          this.context = context;
      }
  
      @Override
      public void setBeanName(String name) {
          this.beanName = name;
      }
  
      @Override
      public void setEmbeddedValueResolver(StringValueResolver resolver) {
          this.strVal = resolver.resolveStringValue("当前操作系统是 ${os.name},计算 30 - 5 为 #{30-5}");
      }
  }
  ```

  ```java
  @Test
  public void test04(){
      Archer archer = context.getBean(Archer.class);
      System.out.println(archer.getContext() == context); // true
      System.out.println(archer.getBeanName()); // archer
      System.out.println(archer.getStrVal()); // 当前操作系统是 Windows 10,计算 30 - 5 为 25
  }
  ```

- 注意：一些 Aware 接口 都也是通过对应的 **xxxAwareProcessor** 对应的 bean 后置处理器进行注入的

## 1.4 Aop

# 第二章 扩展原理

# 第三章 web

