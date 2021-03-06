package com.eureka.client.article.service.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignBasicAuthCustomizeInterceptor implements RequestInterceptor{
	
	public FeignBasicAuthCustomizeInterceptor() {
	}
	
	@Override
	public void apply(RequestTemplate template) {
		System.out.println("=======进入Feign的Interceptor拦截器======");
	}
}
