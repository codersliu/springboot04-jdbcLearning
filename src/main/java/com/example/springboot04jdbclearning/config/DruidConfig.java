package com.example.springboot04jdbclearning.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }

//    后台监控：web.xml， ServletRegistrationBean
    //SpringBoot内置servlet容器，没有web.xml，使用替代类ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //设置后台登陆账号密码
        HashMap<String, String> initParameters = new HashMap<>();

        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");

        //设置白名单，允许访问的对象
        initParameters.put("allow", "");

        //设置黑名单
//        initParameters.put("sliu", "192.168.11.12");

        bean.setInitParameters(initParameters);
        return bean;
    }

    //filter 配置过滤器
    //和拦截器过滤可访问资源不同
    //该过滤器用于过滤哪些资源可以被druid监测到
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParameters = new HashMap<>();

        //不进行监控的资源
        initParameters.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);
        return bean;
    }
}
