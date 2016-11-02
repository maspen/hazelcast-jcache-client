package com.example.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

// https://github.com/hazelcast/hazelcast-code-samples/blob/13b180910abec2cf3605149cb905e2a6fcf5ca39/hazelcast-integration/springboot-caching-jcache/src/main/java/com/hazelcast/springboot/caching/BootifulClient.java
//@SpringBootApplication(scanBasePackages = "com.example.HazelcastSpingCacheClientApplication") doesn't pick up controller
@SpringBootApplication(scanBasePackages = "com.example.client")
//disable Hazelcast Auto Configuration, and use JCache configuration for the client example
@EnableAutoConfiguration(exclude = { HazelcastAutoConfiguration.class })
@EnableCaching
public class HazelcastSpingCacheClientApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HazelcastSpingCacheClientApplication.class, args);
		
		// want to use 'client' configuration
		new SpringApplicationBuilder()
	        .sources(HazelcastSpingCacheClientApplication.class)
	        .profiles("client")
	        .run(args);
	}
}
