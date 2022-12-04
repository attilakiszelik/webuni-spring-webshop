package hu.webuni.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.userservice.model.WebshopUser;

public interface WebshopUserRepository extends JpaRepository<WebshopUser, String>{

	Optional<WebshopUser> findByFacebookId(String facebookId);
	
}