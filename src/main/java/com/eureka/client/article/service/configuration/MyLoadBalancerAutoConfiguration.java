package com.eureka.client.article.service.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.eureka.client.article.service.interceptor.MyLoadBalancerInterceptor;

//@Configuration
public class MyLoadBalancerAutoConfiguration {
	
	//@MyLoadBalanced
	@LoadBalanced
	@Autowired(required = false)
	private List<RestTemplate> restTempLates = Collections.emptyList();
	
	@Bean
	public MyLoadBalancerInterceptor myLoadBalancerInterceptor() {
        return new MyLoadBalancerInterceptor();
    }
	
	@Bean
	public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer() {
		return new SmartInitializingSingleton() {
			@Override
			public void afterSingletonsInstantiated() {
				for(RestTemplate restTemplate : MyLoadBalancerAutoConfiguration.this.restTempLates) {
					List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
					list.add(myLoadBalancerInterceptor());
					restTemplate.setInterceptors(list);
				}
			}
		};
	}
}
