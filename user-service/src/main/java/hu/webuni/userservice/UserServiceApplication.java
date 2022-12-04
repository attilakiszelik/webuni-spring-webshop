package hu.webuni.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.userservice.service.InitDbService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class UserServiceApplication {

	private final InitDbService initDbService;
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		initDbService.createAdmin();
	}
	
}