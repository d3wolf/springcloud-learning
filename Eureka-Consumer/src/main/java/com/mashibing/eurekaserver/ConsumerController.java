package com.mashibing.eurekaserver;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;

@RestController
public class ConsumerController {

	@Autowired
	// 抽象
	DiscoveryClient client;

	@Qualifier("eurekaClient")
	@Autowired
	EurekaClient client2;
	
	@Autowired
	LoadBalancerClient lb;

	@Autowired
	RestTemplate restTemplate;
	

	
	@GetMapping("/client")
	public String client() {
		List<String> services = client.getServices();
		
		for (String str : services) {
			System.out.println(str);
			
		}
		return "Hi";
	}
	@GetMapping("/client3")
	public Object client3() {
		
		List<ServiceInstance> instances = client.getInstances("provider");
		for (ServiceInstance ins : instances) {
			System.out.println(ToStringBuilder.reflectionToString(ins));
		}
		
		return "xxoo";
	}
	@GetMapping("/client4")
	public Object client4() {
		
		// 具体服务
	//	List<InstanceInfo> instances = client2.getInstancesById("localhost:provider:80");
		
		// 使用服务名 ，找列表
		List<InstanceInfo> instances = client2.getInstancesByVipAddress("provider", false);
		
		
		for (InstanceInfo ins : instances) {
			System.out.println(ToStringBuilder.reflectionToString(ins));
		}
		
		if(instances.size()>0) {
			// 服务
			InstanceInfo instanceInfo = instances.get(0);
			if(instanceInfo.getStatus() == InstanceStatus.UP) {
				
			   String url =	"http://" + instanceInfo.getHostName() +":"+ instanceInfo.getPort() + "/getHi";
			
			   System.out.println("url:" + url);
			   
			   RestTemplate restTemplate = new RestTemplate();
			   
			   String respStr = restTemplate.getForObject(url, String.class);
			   
			   System.out.println("respStr:"  + respStr);
			}
			
		}
		return "xxoo";
	}
	
	
	
	
	@GetMapping("/client6")
	public Object client6() {
		
		// ribbon 完成客户端的负载均衡，过滤掉down了的节点
		ServiceInstance instance = lb.choose("provider");
		
		String url ="http://" + instance.getHost() +":"+ instance.getPort() + "/getHi";
		   
		String respStr = restTemplate.getForObject(url, String.class);
		   
		System.out.println("respStr:"  + respStr);

		return respStr;
	}

	@Autowired
	DiscoveryClient discoveryClient;

	@GetMapping("/client7")
	public Object client7() {


		List<ServiceInstance> instances = discoveryClient.getInstances("provider");

		// 自定义轮询算法
		int nextInt = new Random().nextInt(instances.size());
		ServiceInstance instance = instances.get(nextInt);

		String url ="http://" + instance.getHost() +":"+ instance.getPort() + "/getHi";

		String respStr = restTemplate.getForObject(url, String.class);

		System.out.println("respStr:"  + respStr);

		return respStr;
	}


	@GetMapping("/client9")
	public Object client9() {

		// 自动处理url
		String url ="http://provider/getHi";

		String respStr = restTemplate.getForObject(url, String.class);

		System.out.println("respStr:"  + respStr);

		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		System.out.println("response entity:"+entity);
		return respStr;
	}

	@GetMapping("/getMap")
	public Object getMap(){
		// 自动处理url
		String url ="http://provider/getMap";

		ResponseEntity<Map> entity = restTemplate.getForEntity(url, Map.class);
		System.out.println("response entity:"+entity);
		return entity;
	}

}
