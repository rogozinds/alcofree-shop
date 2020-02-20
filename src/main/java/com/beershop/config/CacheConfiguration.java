package com.beershop.config;

import java.time.Duration;

import com.beershop.domain.Product;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.beershop.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.beershop.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.beershop.domain.User.class.getName());
            createCache(cm, com.beershop.domain.Authority.class.getName());
            createCache(cm, com.beershop.domain.User.class.getName() + ".authorities");
            createCache(cm, Product.class.getName());
            createCache(cm, com.beershop.domain.Country.class.getName());
            createCache(cm, com.beershop.domain.Shop.class.getName());
            createCache(cm, com.beershop.domain.Shop.class.getName() + ".fk_shop_countries");
            createCache(cm, com.beershop.domain.Shop.class.getName() + ".country_shops");
            createCache(cm, com.beershop.domain.Country.class.getName() + ".country_shops");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
