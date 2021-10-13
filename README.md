# 主题
学习java的过程记录，应该说是spring，动手实操一下demo

## 条件装配
比较适用的场景，是根据不同运行环境（开发、测试、生产），实例化不同的数据库链接。廖雪峰有比较简单的[讲解](https://www.liaoxuefeng.com/wiki/1252599548343744/1308043874664482)

## AOP有比较巧的用处
教程里面描述了一个自定义的Aspect（配合注解实现），来监控某些方法的性能（执行耗时）
注解的定义
```
@Target(METHOD)
@Retention(RUNTIME)
public @interface MetricTime {
    String value();
}
```
使用注解的service
```
@Component
public class UserService {
    // 监控register()方法性能:
    @MetricTime("register")
    public User register(String email, String password, String name) {
        ...
    }
    ...
}
```
Aspect的实现如下：
```
@Aspect
@Component
public class MetricAspect {
    @Around("@annotation(metricTime)")
    public Object metric(ProceedingJoinPoint joinPoint, MetricTime metricTime) throws Throwable {
        String name = metricTime.value();
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long t = System.currentTimeMillis() - start;
            // 写入日志或发送至JMX:
            System.err.println("[Metrics] " + name + ": " + t + "ms");
        }
    }
}
```
明确定义@Around切面只对使用了MetricTime注解的方法生效。


# 运行
```
mvn clean package
```

# 遇到的坑

## 001，打包后运行说找不到Main入口文件
需要配置maven的plugin，例如maven-shade-plugin，指定main入口。


## 002，jsr250-api 与 javax.annotation-api的区别
> 使用@PostConstruct是需要引入
1、jsr250-api与 javax.annotation-api都是关于J2EE的注释的API
2、目前jsr250-api已经被移入了 javax.annotation-api中，也就是说， javax.annotation-api中包括了jsr250-api。所以只需要用javax.annotation-api就足够了。
3、jsr250-api 的官方解释是 JSR-250 Reference Implementation by Glassfish ，即实现了JSR-250标准的注释。JSR是Java Specification Requests的缩写，意思是Java 规范提案。
而 javax.annotation-api的官方解释是Common Annotations for the JavaTM Platform API ，即JavaTM平台API的常见注释。