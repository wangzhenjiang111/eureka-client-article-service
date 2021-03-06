package com.eureka.client.article.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eureka.client.article.service.bean.FirstAlwaysRule;
import com.netflix.loadbalancer.IRule;

//@Configuration
public class MyRuleConfiguration{
	
	@Bean
	public IRule getRule() {
		return new FirstAlwaysRule();
	}

}
