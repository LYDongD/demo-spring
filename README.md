## demo-spring项目导读

### springboot starter定义攻略

> 1 如何让配置模块自动装配？

定义一个springboot-starter，springboot会自动装配配置模块

1.1 快速构建一个springboot的项目

https://start.spring.io/

> 1.2 流程

A. 定义pom, parent指定springboot-parent, 引入springboot-starter依赖

* 注意这里不需要引入spring-boot-maven-plugin，因为它不需要被打包成一个可执行的jar，不必引入其依赖的jar

B. 定义一个需要自动装配的组件

C. 定义一个自动配置类进行组件的配置，组件依赖外部属性配置

D. 【重点】定义resource/META-INF/spring.factories，指定自动配置类

org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.demo.demospringbootstarter.DemoConfig

E. 构建该starter，安装到本地maven仓库

> 1.3 引入starter

通过maven引入starter依赖，启动应用时，starter指定的自动配置类(spring.factories)将被加载，完成组件的装配

如果组件依赖外部属性，只需要在application.properties定义即可

