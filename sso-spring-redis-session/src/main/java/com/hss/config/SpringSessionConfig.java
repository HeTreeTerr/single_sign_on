package com.hss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;

@Configuration
public class SpringSessionConfig {

    /**
     * 更换序列化器
     * @return
     */
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer setSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
    * Redis内session过期事件监听
    */
    @EventListener
    public void onSessionExpired(SessionExpiredEvent expiredEvent) {
        String sessionId = expiredEvent.getSessionId();
        System.out.println("Redis内session过期事件监听--"+sessionId);
    }


    /**
      * Redis内session删除事件监听
      */
    @EventListener
    public void onSessionDeleted(SessionDeletedEvent deletedEvent) {
        String sessionId = deletedEvent.getSessionId();
        System.out.println("Redis内session删除事件监听--"+sessionId);
     }

     /**
       * Redis内session保存事件监听
       */
     @EventListener
     public void onSessionCreated(SessionCreatedEvent createdEvent) {
         String sessionId = createdEvent.getSessionId();
         System.out.println("Redis内session保存事件监听--"+sessionId);
     }

}
