package hu.webuni.userservice.web;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.userservice.api.UserServiceApi;
import hu.webuni.userservice.api.LoginDto;
import hu.webuni.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WebshopUserController implements UserServiceApi{
	
	private final AuthenticationManager authenticationManager; 
	private final JwtService jwtService;

	//private final WebshopUserService webshopUserService;
	
//	@GetMapping("/fbLoginSucces")
//	public String onFacebookLoginSuccess(Map<String, Object> model, OAuth2AuthenticationToken authenticationToken, @AuthenticationPrincipal OAuth2User principal) {
//		
//		String fullname = authenticationToken.getPrincipal().getAttribute("name");
//		
//		fullname = principal.getAttribute("name");
//		model.put("fullName", fullname);
//		
//		webshopUserService.registerUserIfNotExists(authenticationToken);
//		
//		return "home";
//		
//	}

	@Override
	public String login(@RequestBody LoginDto loginDto) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		
		return jwtService.createJwtToken((UserDetails)authentication.getPrincipal());

	}

	@Override
	public String register() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registerWithFb() {
		// TODO Auto-generated method stub
		return null;
	}

	
}