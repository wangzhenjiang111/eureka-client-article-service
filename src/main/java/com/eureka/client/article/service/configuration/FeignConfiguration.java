package com.eureka.client.article.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eureka.client.article.service.interceptor.FeignBasicAuthCustomizeInterceptor;

import ch.qos.logback.core.encoder.Encoder;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;

@Configuration
public class FeignConfiguration {
	
	/**
	 * 日志级别
	 */	
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
	
	/**
	 * Feign提供的拦截器
	 */
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor1() {
		return new BasicAuthRequestInterceptor("user", "password");
	}
	
	/**
	 * 自定义拦截器
	 */
	@Bean
	public FeignBasicAuthCustomizeInterceptor basicAuthRequestCustomizeInterceptor() {
		return new FeignBasicAuthCustomizeInterceptor();
	}
	
	/**
	 * 超时时间配置
	 */
	@Bean
	public Request.Options options(){
		System.out.println("=====超时时间配置======");
		return new Request.Options(1000, 5000);
	}
	
	/**
	 * 自定义编码器与解码器
		@Bean
		public Decoder decoder() {
		    return new MyDecoder();
		}
		@Bean
		public Encoder encoder() {
		    return new MyEncoder();
		}
	 */
	
}
