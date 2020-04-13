package com.mashibing.eurekaserver;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EurekaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerApplication.class, args);
	}


	@Bean
	@LoadBalanced //把url转为服务器地址
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

//	@Bean
//	public IRule myRule(){
////		return new RoundRobinRule();
//		return new RandomRule();
//	}
}
