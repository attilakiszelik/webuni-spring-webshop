package hu.webuni.userservice.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", url="${feign.userservice.url}")
public interface UserServiceApi {

	@PostMapping("/login")
	String login(@RequestBody LoginDto loginDto);
	
	@PostMapping("/register")
	String register();
	
	@PostMapping("/registerWithFb")
	String registerWithFb();
	
}
