package com.eureka.client.article.service.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.client.article.service.Feign.UserRemoteFeign;
import com.eureka.client.article.service.Feign.UserRemoteFeignWithHystrix;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class UserCallController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@Autowired
	private UserRemoteFeign userRemoteFeign;
	
	@Autowired
	private UserRemoteFeignWithHystrix userRemoteFeignWithHystrix;
	
	/*
	 * @GetMapping("/article/callHello") public String callHello() { return
	 * restTemplate.getForObject("http://localhost:8081/user/hello", String.class);
	 * }
	 */
	
	// ribbon api
	@GetMapping("/choose")
	public Object chooseUrl() {
		ServiceInstance instance = loadBalancer.choose("eureka-client-user-service");
		return instance;
	}
	
	@GetMapping("/user/hello")
	public String callHello1() {
		return restTemplate.getForObject("http://eureka-client-user-service/user/hello", String.class);
	}
	
	@GetMapping("/article/callHello2")
	public String callHello2() {
		return restTemplate.getForObject("http://eureka-client-user-service/user/hello", String.class);
	}
	
	@GetMapping("/article/callHello3")
	public String callHello3() {
		String result = userRemoteFeign.hello();
		System.out.println("调用结果：" + result);
		return result;
	}
	
	// hystrix api
	@GetMapping("/article/callHello4")
	@HystrixCommand(fallbackMethod = "defaultCallHello", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "fallback.enabled", value = "true")
	})
	public String callHello4() {
		String result = restTemplate.getForObject("http://eureka-client-user-service/user/hello", String.class);
		return result;
	}
	
	public String defaultCallHello() {
		return "Hystrix execute";
	}
	
	@GetMapping("/article/callHello5")
	public String callhello5() {
		String result = userRemoteFeignWithHystrix.hello();
		System.out.println("调用结果：" + result);
		return result;
	}
}
