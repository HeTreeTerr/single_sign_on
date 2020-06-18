package com.hss.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    //配置Druid的监控
    //1.配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean<Servlet> statViewServlet(){
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");

        Map<String,String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","root");
        //允许谁登陆，不写或为null允许所有
        //initParameters.put("allow","");
        //拒绝谁访问
        //initParameters.put("deny","192.168.0.1");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }
    //2.配置监控的filter
    @Bean
    public FilterRegistrationBean<Filter> webStatFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());

        Map<String,String> initParameters = new HashMap<>();
        initParameters.put("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParameters);
        //设置要拦截的路径，不设置是默认拦截所有请求
        filterRegistrationBean.addUrlPatterns("/*");
        //filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
