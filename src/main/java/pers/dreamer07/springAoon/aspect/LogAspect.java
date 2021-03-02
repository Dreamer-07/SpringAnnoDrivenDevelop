package pers.dreamer07.springAoon.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 日志切面类
 * @program: springAoon
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-02
 **/
@Aspect // 标识切面类
public class LogAspect {

    /**
     * 使用 @PointCut 完成对公共切入点表达式的抽取
     */
    @Pointcut("execution(* pers.dreamer07.springAoon.service.*.*(..))")
    public void pointName(){};

    /**
     * 使用注解：
     *  @Before: 前置通知
     * @param joinPoint 连接点信息类
     */
    @Before("pointName()")
    public void Before(JoinPoint joinPoint){
        System.out.println("@Before：切入点方法名：" + joinPoint.getSignature().getName() + ", 参数列表：" + Arrays.toString(joinPoint.getArgs()));
    };

    @After("pers.dreamer07.springAoon.aspect.LogAspect.pointName()") // 后置通知
    public void After(){
        System.out.println("@After 后置通知");
    }

    @AfterReturning(returning = "result", value = "pointName()") // 返回通知
    public void AfterReturning(Object result){
        System.out.println("@AfterReturning: 返回结果 " + result);
    }

    @AfterThrowing(throwing = "exception", value = "pointName()") // 异常通知
    public void AfterThrowing(Exception exception){
        System.out.println("@AfterThrowing: 抛出异常 " + exception);
    }

    @Around("pointName()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("@Around: 环绕前通知");
        // 调用 joinPoint.proceed() 调用目标方法
        Object result = joinPoint.proceed();
        System.out.println("@Around: 环绕后通知");
        return result;
    }


}
