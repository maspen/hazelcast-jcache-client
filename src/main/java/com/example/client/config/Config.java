package com.example.client.config;

import java.util.HashMap;
import java.util.Map;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.example.common.repository.IProductRepository;
import com.example.common.repository.ProductRepositoryImpl;
import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MulticastConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
@EnableCaching
public class Config {
	
	// https://github.com/hazelcast/hazelcast-code-samples/blob/13b180910abec2cf3605149cb905e2a6fcf5ca39/hazelcast-integration/spring-jcache/src/main/java/com/hazelcast/spring/jcache/AppConfig.java
	
	@Bean
	public IProductRepository productRepository() {
		return new ProductRepositoryImpl();
	}
	
	@Bean
	// we want to have Hazelcast member started before client JCache provider
    @DependsOn("instance")
	public CacheManager cacheManager() {
		CachingProvider cachingProvider = Caching.getCachingProvider();
		javax.cache.CacheManager cacheManager = cachingProvider.getCacheManager();
		return new JCacheCacheManager(cacheManager);
	}
	
	@Bean
    public HazelcastInstance instance() {
        Map<String, CacheSimpleConfig> cacheConfigs = new HashMap<String, CacheSimpleConfig>();
        cacheConfigs.put("productCache", new CacheSimpleConfig().setName("productCache"));
        com.hazelcast.config.Config config = new com.hazelcast.config.Config().setCacheConfigs(cacheConfigs);
        
        GroupConfig groupConfig = new GroupConfig("bootifulcluster", "bootiful-pass");
        config.setGroupConfig(groupConfig);
        
        NetworkConfig networkConfig = new NetworkConfig();
        JoinConfig joinConfig = new JoinConfig();
        MulticastConfig multiCastConfig = new MulticastConfig();
        multiCastConfig.setEnabled(true);
        joinConfig.setMulticastConfig(multiCastConfig);
        networkConfig.setJoin(joinConfig);
        
        config.setNetworkConfig(networkConfig);
        
        return Hazelcast.newHazelcastInstance(config);
    }
}
