package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private ClientRegistrationRepository repo;
	
	@Override
	public void configure(HttpSecurity http)throws Exception {
		http.authorizeRequests().antMatchers("/","/login","/home")
		.permitAll().anyRequest().authenticated().and().formLogin().and().oauth2Login();
	}

}
