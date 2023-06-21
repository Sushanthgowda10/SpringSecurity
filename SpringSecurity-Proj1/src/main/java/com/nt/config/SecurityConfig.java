package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("sushanth").password("{noop}K10").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("ashik").password("{noop}K11").roles("MANAGER");
	}
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	
	//Authorized request
	
	http.authorizeHttpRequests().antMatchers("/").permitAll()//NO authentication and no authorized
	.antMatchers("/offers").authenticated() // only authentication
     .antMatchers("/balance").hasAnyRole("CUSTOMER","MANAGER")
     .antMatchers("/loanApproval").hasRole("MANAGER").anyRequest().authenticated()
     
     //Specific authentication mode
//     .and().httpBasic();
	
	.and().formLogin().and().rememberMe().and().logout()
	
	.logoutRequestMatcher(new AntPathRequestMatcher("/signOut"))
	
	//expected /error handling (for 403 error)
	.and().exceptionHandling().accessDeniedPage("/denied")
	
	//add sessionMaxConcurrency
	.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
}
	
	
	
}

