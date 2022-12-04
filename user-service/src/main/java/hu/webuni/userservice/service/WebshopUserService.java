package hu.webuni.userservice.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import hu.webuni.userservice.model.WebshopUser;
import hu.webuni.userservice.repository.WebshopUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebshopUserService {

	private final OAuth2AuthorizedClientService authClientService;
	private final WebshopUserRepository userRepository;
	
	public void registerUserIfNotExists(OAuth2AuthenticationToken authenticationToken) {
		
	//ALAPADATOK
		
		//tokenből getName()-mel közvetlenül kinyerhető a facebookId
		String facebookId = authenticationToken.getName();
		System.out.println(facebookId);

		//tokenből kinyert OAuth2User-en keresztül kinyerhető ugyanúgy a facebookId, de a név és email cím is
		OAuth2User oauth2user = authenticationToken.getPrincipal();
		System.out.println("facebookId: " + oauth2user.getName());
		System.out.println("name: " + oauth2user.getAttribute("name"));
		System.out.println("email: " + oauth2user.getAttribute("email"));
		
	//ACCESS TOKEN
		
		//ha a tokenből kinyerjük, hogy mi az autorizációs kliensünk (pl.: facebook, google, stb.)
		String authorizedClientRegistrationId = authenticationToken.getAuthorizedClientRegistrationId(); 
		//akkor az OAuth2AuthorizedClientService segítségével (megadva az autorizációs kliens típusát és a userünk ID-jét) kinyerhetünk egy OAuth2AuthorizedClient-et 
		OAuth2AuthorizedClient client = authClientService.loadAuthorizedClient(authorizedClientRegistrationId, facebookId);
		//azon keresztül pedig már kinyerhető az access token, amivel további adatokhoz férhetünk hozzá (pl.: profilkép, postok, stb.)
		System.out.println("access token: " + client.getAccessToken().getTokenValue());
		
	//USER MENTÉSE
		
		//lekérdezzük, hogy facebookId alapján megtalálható-e az adatbázisunkban
		Optional<WebshopUser> optionalExistingUser = userRepository.findByFacebookId(facebookId);
		//ha nem
		if(optionalExistingUser.isEmpty()) {
			//akkor mentjük
			WebshopUser newUser = new WebshopUser(oauth2user.getAttribute("email").toString(), "", Set.of("user"), facebookId);
			userRepository.save(newUser);
		}
		
	}

}
