package com.mashibing.UserProvider;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashibing.UserAPI.Person;
import com.mashibing.UserAPI.UserApi;

@RestController
public class UserController implements UserApi {

	
	@Value("${server.port}")
	String port;
	
	
	private AtomicInteger count = new AtomicInteger();
	
	@Override
	public String alive() {

		try {
			System.out.println("准备睡");

			if("81".equals(port)){
				Thread.sleep(2500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if("81".equals(port)){
			int i = 1/0;
		}

		int i = count.getAndIncrement();
		System.out.println(port + " 好的 ====第：" + i + "次调用");
		return "provider port:" + port + ", invoke count:" + i;
	}

	@Override
	@GetMapping("/getById")
	public String getById(Integer id) {
		Person p = new Person();
		p.setId(id);
		p.setName("Name_"+id);
		String s = ToStringBuilder.reflectionToString(p);
		return s;
	}
	
	@GetMapping("/getMap")
	public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
		
		
		
		System.out.println(id);
		return Collections.singletonMap(id, "mmeme");
	}
	@GetMapping("/getMap2")
	public Map<Integer, String> getMap2(Integer id,String name) {
		// TODO Auto-generated method stub
		System.out.println(id);
		return Collections.singletonMap(id, name);
	}
	
	@GetMapping("/getMap3")
	public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println(map);
		return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
	}
	
	
	@PostMapping("/postMap")
	public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println(map);
		return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
	}

	@Override
	public Person postPerson(Person person) {
		System.out.println(ToStringBuilder.reflectionToString(person));
		return person;
	}

}
