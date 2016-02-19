package com.example.springboot;

/**
 * Created by Deven on 2016-02-18.
 */
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

    @Bean
    public DataSource druidDataSource(
            @Value("${spring.datasource.driverClassName}") String driver,
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.max-active}") int maxActive,
            @Value("${spring.datasource.max-idle}") int maxIdle,
            @Value("${spring.datasource.min-idle}") int minIdle,
            @Value("${spring.datasource.initial-size}") int initialSize,
            @Value("${spring.datasource.validation-query}") String validationQuery,
            @Value("${spring.datasource.validation-query-timeout}") int validationQueryTimeout,
            @Value("${spring.datasource.test-on-borrow}") boolean testOnBorrow,
            @Value("${spring.datasource.test-on-return}") boolean testOnReturn,
            @Value("${spring.datasource.test-while-idle}") boolean testWhileIdle,
            @Value("${spring.datasource.max-wait-millis}") int maxWaitMillis,
            @Value("${spring.datasource.min-evictable-idle-time-millis}") int minEvictableIdelTimeMillis,
            @Value("${spring.datasource.time-between-eviction-runs-millis}") int timeBetweenEvictionRunsMillis) {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);

        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxIdle(maxIdle);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setInitialSize(initialSize);

        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setValidationQueryTimeout(validationQueryTimeout);

        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setTestWhileIdle(testWhileIdle);

        druidDataSource.setMaxWait(maxWaitMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdelTimeMillis);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
