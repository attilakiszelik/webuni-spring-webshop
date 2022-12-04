package hu.webuni.userservice.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.userservice.api.LoginDto;
import hu.webuni.userservice.api.UserServiceApi;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WebshopUserController implements UserServiceApi{
	
//	private final AuthenticationManager authenticationManager; 
//	private final JwtService jwtService;
//
//	private final WebshopUserService webshopUserService;
//	
//	@GetMapping("/fbLoginSucces")
//	public String onFacebookLoginSuccess( OAuth2AuthenticationToken authenticationToken) {
//		
//		webshopUserService.registerUserIfNotExists(authenticationToken);
//		
//		return jwtService.createJwtToken((UserDetails)authenticationToken.getPrincipal());
//		
//	}

	@Override
	public String login(@RequestBody LoginDto loginDto) {
		
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//		
//		return jwtService.createJwtToken((UserDetails)authentication.getPrincipal());
		
		return "jónapot";

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