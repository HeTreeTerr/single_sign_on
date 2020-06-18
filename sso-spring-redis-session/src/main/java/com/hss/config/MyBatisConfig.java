package com.hss.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

//自定义mybatis的配置规则
//@MapperScan(value = "com.itcast.springboot.mapper"),批量扫描要识别的mapper.省去写@Mapper
@org.springframework.context.annotation.Configuration
@MapperScan(value = "com.hss.mapper")
public class MyBatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return  new ConfigurationCustomizer(){
            @Override
            public void customize(Configuration configuration) {
                //开启驼峰命名法
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
