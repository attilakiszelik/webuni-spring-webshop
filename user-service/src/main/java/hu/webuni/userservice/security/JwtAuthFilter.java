package hu.webuni.userservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	
	private final JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//a request headerjének lekérése
		String authHeader = request.getHeader(AUTHORIZATION);
		
		//a metódus hívása az authHeader átadásával (visszatérési értékét eltároljuk egy változóban)
		UsernamePasswordAuthenticationToken authentication = createUserDetailsFromAuthHeader(authHeader, jwtService);
		
		//vizsgálni kell, hogy nem null értékkel tért vissza a metódus
		if(authentication != null) {
			
			//ha érdekes, hogy milyen IP-ről érkezik vissza a token, akkor ezt is be kell állítani a SecurityContextHolder-nek történő átadás előtt
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			//authentikáció átadása a SecurityContextHolder-nek
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
		}
		
		//a kérés és a válasz tovább engedése
		filterChain.doFilter(request, response);
		
	}

	public static UsernamePasswordAuthenticationToken createUserDetailsFromAuthHeader(String authHeader, JwtService jwtService) {
		
		//csak akkor történik vizsgálat, ha az authHeader nem null és "Bearer "-rel kezdődik 
		if(authHeader != null && authHeader.startsWith(BEARER)) {
			
			//az authHeader elejéről le kell vágni a "Bearer " előtagot
			String jwtToken = authHeader.substring(BEARER.length());
			
			//az így kapott token visszafejtése UserDetails-é
			UserDetails principal = jwtService.parseJwt(jwtToken);
			
			//majd a UserDetails adatainak átadása egy UsernamePasswordAuthenticationToken típusú változónak
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
			
			//visszatérés
			return authentication;
		}
		
		//ha nem sikerült a token előállítása
		return null;
	}
	
}
