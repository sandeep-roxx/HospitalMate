package com.verma.sandeep.hospital.mate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.verma.sandeep.hospital.mate.constant.UserRole;
import com.verma.sandeep.hospital.mate.filter.SecurityFilter;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(encoder);
		authProvider.setUserDetailsService(userDetailsService);
		return authProvider;
		
	}
	
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain filterChain(HttpSecurity http) {
		
		http.csrf(csrf->csrf.disable())
		        .authorizeHttpRequests(req->req
		        		.requestMatchers("/patient/register","/user/login").permitAll()
		        		.requestMatchers("/patient/all").hasAuthority(UserRole.ADMIN.name())
		        		.requestMatchers("/spec/**").hasAuthority(UserRole.ADMIN.name())
		        		.requestMatchers("/doctor/**").hasAuthority(UserRole.ADMIN.name())
		        		.requestMatchers("/slot/all","/slot/update-status").hasAuthority(UserRole.ADMIN.name())
		        		.requestMatchers("/ward/**").hasAuthority(UserRole.ADMIN.name())
		        		.requestMatchers("/employee/register","/employee/delete","/employee/all").hasAuthority(UserRole.ADMIN.name())
		        		.requestMatchers("/appointment/register","/appointment/update","/appointment/all").hasAuthority(UserRole.ADMIN.name())
		        		.requestMatchers("/appointment/search","/appointment/view/slots").hasAuthority(UserRole.PATIENT.name())
		        		.requestMatchers("/slot/book","/slot/cancel/slot-request","/slot/patient/my-slots","/slot/payment").hasAuthority(UserRole.PATIENT.name())
		        		.requestMatchers("/slot/doctor/my-slots").hasAuthority(UserRole.DOCTOR.name())
		        		.anyRequest() .authenticated()
		        )
		        .exceptionHandling(ex -> ex //Register the Entry Point
		                .authenticationEntryPoint(authenticationEntryPoint) // Handle unauthorized access
		        )
		        .sessionManagement(session -> session
		                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session for JWT
		        )
		        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);// Register custom filter
		return http.build();
	}

}
