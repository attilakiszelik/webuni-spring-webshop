package hu.webuni.userservice.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.userservice.model.WebshopUser;
import hu.webuni.userservice.repository.WebshopUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitDbService {

	private final WebshopUserRepository webshopUserRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public void createAdmin() {
		
		webshopUserRepository.save(new WebshopUser("admin", passwordEncoder.encode("admin"), Set.of("admin", "customer"), ""));
		
	}
	
}
