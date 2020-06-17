package com.hss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @EnableRedisHttpSession开启redis对SpringSession的支持
 * maxInactiveIntervalInSeconds    设置 Session 的失效时间，单位为秒。默认(1800 秒)30 分钟。
 * redisNamespace	为键定义唯一的命名空间。该值用于通过更改前缀与默认 spring:session 隔离会话
 * redisFlushMode	Redis 会话的刷新模式。默认值为“保存”
 * cleanupCron	过期会话清理作业的 cron 表达式。默认值(“0 *****”)每分钟运行一次。
 */
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SsoSpringRedisSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoSpringRedisSessionApplication.class, args);
    }

}
