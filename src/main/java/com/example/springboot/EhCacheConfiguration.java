package com.example.springboot;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Deven on 2016-02-18.
 * Only enable when we must use it in our source code explicitly. Not for Hibernate.
 */
//@Configuration
//@EnableCaching
@Deprecated
public class EhCacheConfiguration {

    /*
     * ehcache 主要的管理器
     */
    //@Bean(name = "ehCacheCacheManager")
    public EhCacheCacheManager hibernateCacheManager(EhCacheManagerFactoryBean factoryBean) {
        return new EhCacheCacheManager(factoryBean.getObject());
    }

    /*
     * 根据shared与否的设置, Spring分别通过CacheManager.create() 或 new CacheManager()方式来创建一个ehcache基地.
     */
    //@Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
}
