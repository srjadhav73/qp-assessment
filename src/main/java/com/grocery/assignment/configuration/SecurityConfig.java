package com.grocery.assignment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.grocery.assignment.security.CustomUserDetailsService;

import jakarta.annotation.security.PermitAll;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private static final String[] PUBLIC_URLS = new String[] {
			"/api/user/create","/api/grocery/"
	};
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
				return http
						.csrf(t -> t.disable())
						.authorizeHttpRequests(auth->{
							auth.requestMatchers(PUBLIC_URLS).permitAll()
							.anyRequest().authenticated();
						})
						.httpBasic(Customizer.withDefaults())
						.authenticationProvider(authenticationProvider())
						.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider  daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
}
