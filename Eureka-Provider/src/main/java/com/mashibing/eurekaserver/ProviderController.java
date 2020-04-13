package com.mashibing.eurekaserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class ProviderController {

	@Value("${server.port}")
	String port;

	@GetMapping("/getHi")
	public String getHi() {
		
		return "Hi, My port is " + port;
	}

	@GetMapping("/getMap")
	public Map<String, Object> getMap(){
		return Collections.singletonMap("port", port);
	}

	@GetMapping("/getObj")
	public Person getObj(){
		return new Person(1, "abc");
	}
}
