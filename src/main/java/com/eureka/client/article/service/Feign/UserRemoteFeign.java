package com.eureka.client.article.service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.eureka.client.article.service.configuration.FeignConfiguration;

@FeignClient(value = "eureka-client-user-service", configuration = FeignConfiguration.class)
public interface UserRemoteFeign {

	@GetMapping("/user/hello")
	public String hello();
}