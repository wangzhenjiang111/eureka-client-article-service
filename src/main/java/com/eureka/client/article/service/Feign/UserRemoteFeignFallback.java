package com.eureka.client.article.service.Feign;

import org.springframework.stereotype.Component;

@Component
public class UserRemoteFeignFallback implements UserRemoteFeignWithHystrix{
	
	@Override
	public String hello() {
		return "fail";
	}
}
