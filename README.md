# learn-spring
learn-spring-note

#### 书籍参考：spring-action， 跟随书中项目： Taco-Cloud

+ ####LiveReload插件安装
    * Chrome安装，需要点击使图标中心变为实心点
      * Site Access需要设置为On all sites或者On specific sites，On Click会报错
    
+ 输入符合信用卡Credit Card Number验证的值比较麻烦，可以用 http://www.getcreditcardnumbers.com/ 生成
+ 书中很多很多地方讲解不完整，需要自行解决才能完成
    * 参考 google & stack overflow
    * https://github.com/habuma/spring-in-action-5-samples
    
 + JPA domain-specific language (DSL)
    * verb+[subject]+By+predicate
    * findByDeliveryZip()
    * readOrdersByDeliveryZipAndPlacedAtBetween()
    * many operators
    * @Query 注解 有些查询通过方法命名很难实现，使用注解明确方法调用时要执行的查询
    
 + 通过将security starter添加到项目的构建文件中，我们得到如下的安全特性
    * 所有的http请求都需要认证
    * 不需要特定的角色和权限
    * 没有登录页面
    * 认证过程只是通过HTTP basic认证对话框实现的
    * 系统只有一个用户，用户名为user
 
 + spring security
    * User store
        * in memory
        * database
        * LDAP, TODO: LDAP暂时忽略
    * register
    * login
    *  logout
    * WebSecurityConfigurerAdapter
    *   User & UserRepository
    *  UserDetailsService
    *  Controller中确定User
    *  Authentication 
        * @AuthenticationPrincipal Principal
 
 + Configuration
    * application.yml/application.properties
    * properties metadata: src/resource/META-INF/additional-spring-configuration-metadata.json
    * 生产环境和开发环境配置
        * 环境变量
            export SPRING_DATASOURCE_URL=jdbc:mysql://localhost/tacocloud
            export SPRING_DATASOURCE_USRERNAME=tacouser
            export SPRING_DATASOURCE_PASSWORD=tacopassword
        
    