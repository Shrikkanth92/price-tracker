package com.pricetracker;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories("com.pricetracker.repositories")
@EnableScheduling
public class PriceTrackerApplication extends AsyncConfigurerSupport {

	public static void main(String[] args) {
		SpringApplication.run(PriceTrackerApplication.class, args);
	}
	
	 @Override
	 public Executor getAsyncExecutor() {
	        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
	        threadPoolExecutor.setCorePoolSize(2);
	        threadPoolExecutor.setMaxPoolSize(2);
	        threadPoolExecutor.setQueueCapacity(500);
	        threadPoolExecutor.setKeepAliveSeconds(1000);
	        threadPoolExecutor.setThreadNamePrefix("CronJob");
	        threadPoolExecutor.initialize();
	        return threadPoolExecutor;
	    }
}
