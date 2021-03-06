package com.eureka.client.article.service.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.client.article.service.bean.HouseInfo;

@RestController
public class HouseCallController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/call/data")
	public HouseInfo getData(@RequestParam("name") String name) {
	    return restTemplate.getForObject("http://eureka-client-user-service/house/data?name="+ name, HouseInfo.class);
	}
	
	@GetMapping("/call/data/{name}")
	public String getData2(@PathVariable("name") String name) {
		return restTemplate.getForObject("http://eureka-client-user-service/house/data/{name}", String.class, name);
	}
	
	@GetMapping("/call/dataEntity")
	public HouseInfo getData3(@RequestParam("name") String name) {
		ResponseEntity<HouseInfo> responseEntity = restTemplate.getForEntity("http://eureka-client-user-service/house/data?name="+ name, HouseInfo.class);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			System.out.println(responseEntity);
			return responseEntity.getBody();
		}
		return null;
	}
	
	@GetMapping("/call/save")
	public String save() {
		 HouseInfo houseInfo = new HouseInfo("北京", "大兴", "机场小区");
		 String city = restTemplate.postForObject("http://eureka-client-user-service/house/save", houseInfo, String.class);
		 return city;
	}
}
