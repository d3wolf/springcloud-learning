package com.mashibing.UserAPI;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/User")
public interface UserApi {

	/**
	 * 老师，工作中有专门起一个公共api服务的吗
	 * 
	 * 查看当前服务状态~~~
	 * @return (* ￣3)(ε￣ *)
	 */
	@GetMapping("/alive")
	public String alive();
	
	@GetMapping("/getById")
	public String getById(@RequestParam("id") Integer id); //必须写RequestParam，不然不传参数
	
	
	@PostMapping("/postPerson")
	public Person postPerson(@RequestBody Person person);
}
