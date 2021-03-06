package com.eureka.client.article.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableTurbine
public class ArticleServiceApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ArticleServiceApp.class, args);

	}
	
	@Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
        ServletRegistrationBean<HystrixMetricsStreamServlet> registration = new ServletRegistrationBean<HystrixMetricsStreamServlet>(new HystrixMetricsStreamServlet());
        registration.addUrlMappings("/actuator/hystrix.stream");
        return registration;
    }

}
