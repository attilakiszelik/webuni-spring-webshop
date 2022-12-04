package hu.webuni.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final WebshopUserDetailsService webshopUserDetailsService;
	private final JwtAuthFilter jwtAuthFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.authenticationProvider(myAuthenticationProvider());
		
	}
	
	@Bean
	public AuthenticationProvider myAuthenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(webshopUserDetailsService);
		return daoAuthenticationProvider;
		
	}
	
	//JWT generálásának és ellenőrzésének beállítása után a http basic helyett annak használata, tulajdonképpen úgy történik, hogy
	//azt be kell állítani a Spring Security által használt számos filter közül a UsernamePasswordAuthnticationFilter elé!
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			//facebook login beépítése
			.antMatchers(HttpMethod.POST,"/oauth2/**").permitAll()
			.antMatchers(HttpMethod.POST,"/fbLoginSuccess").permitAll()
			.antMatchers(HttpMethod.POST,"/api/login/**").permitAll()
			.antMatchers(HttpMethod.POST,"/userservice/login/**").permitAll()
			//catalog-service
			//.antMatchers(HttpMethod.POST,"/api/.../**").hasAuthority("ADMIN")
			//.antMatchers(HttpMethod.PUT,"/api/.../**").hasAuthority("ADMIN")
			//.antMatchers(HttpMethod.DELETE,"/api/.../**").hasAuthority("ADMIN")
			//.antMatchers(HttpMethod.PUT,"/api/.../**").hasAnyAuthority("CUSTOMER","ADMIN") 
			.anyRequest().authenticated()
			//facebook login beépítése
			.and()
			.oauth2Login()
			.defaultSuccessUrl("/fbLoginSuccess", true)
			; 

		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
