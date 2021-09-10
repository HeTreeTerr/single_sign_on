# 1 single_sign_on（单点登录）

## 1.1 sso-spring-session
>了解spring实现session的思路和原理，实现session信息持久化至mysql数据库。
  
使用步骤：
1. 创建数据库，执行sql语句：sql/spring_session.sql
2. 修改项目数据库配置，启动项目
2. 访问测试地址：http://localhost:8080/test
3. 在数据库中查看持久的session信息。

## 1.2 sso-spring-redis-session


## 1.3 hss-sso-jwt
### 1.3.1 shar-page
包含redis的连接工具类，jwt生成钥匙、加密解密工具类，项目信息传输对象。
是项目完成功能的核心基础。包含全局过滤器，可以从请求头中获取用户token,
并效验，完成身份识别。将此项目作为jar引入项目2、3中，降低代码的耦合。
### 1.3.2 make-jwt-token
包含登录、获取用户基本信息、刷新token、退出清除token等接口。
### 1.3.3 my_sys1
包含效验接口、退出接口。主要用来检测单点登录是否成功！

### 1.3.4 hookAjax（js ajax请求拦截器）
由于无法找到java操作localStorage的方法，使用hookAjax配合ajax请求，达到及时清除
替换失效或快失效的token。

### 1.3.5 使用nginx代理
启动项目2和项目3，并用nginx代理服务。让两个项目可以共享域名，实现localStorage的
共享，达到一次登录，多平台公用的目的。配合js使提升用户体验感！