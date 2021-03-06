package com.eureka.client.article.service.Feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class UserRemoteFeignFallbackFactory implements FallbackFactory<UserRemoteFeignWithHystrix>{

	private Logger logger = LoggerFactory.getLogger(UserRemoteFeignFallbackFactory.class);
	
	@Override
	public UserRemoteFeignWithHystrix create(final Throwable cause) {
		logger.error("UserRemoteFeignWithHystrix回退：", cause);
		return new UserRemoteFeignWithHystrix() {
			@Override
			public String hello() {
				return "fail";
			}
		};
	}
}
