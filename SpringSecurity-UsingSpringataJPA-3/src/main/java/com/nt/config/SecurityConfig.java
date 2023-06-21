package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nt.service.IUserService;
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
//	@Autowired
//	private DataSource ds;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder())
//		.usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERS WHERE UNAME=?")
//		.authoritiesByUsernameQuery("SELECT UNAME,ROLE FROM USER_ROLES WHERE UNAME=?");
//		}
	
	@Autowired
	private IUserService service;
	
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(encoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//auth.userDetailsService(service).passwordEncoder(encoder)
		//Authorized request
		
		http.authorizeHttpRequests().antMatchers("/bank/").permitAll()//NO authentication and no authorized
		
		.antMatchers("/user/register","/user/showlogin").permitAll()
		.antMatchers("/bank/balance").authenticated()	//only	
		.antMatchers("/bank/offers").authenticated() // only authentication
	     .antMatchers("/bank/balance").hasAnyAuthority("CUSTOMER","MANAGER")
	     .antMatchers("/bank/loanApprove").hasAuthority("MANAGER").anyRequest().authenticated()
	     .and().formLogin().defaultSuccessUrl("/bank/",true)
	     .loginPage("/user/showlogin")
	     .loginProcessingUrl("/login")
	     .failureUrl("/user/showlogin?error")
//	    .loginPage("/user/showlogin")
//	    .login
//	     //Specific authentication mode
////	     .and().httpBasic();
//		
	//.and().formLogin()
	.and().rememberMe().and().logout()
//		
//		
//		
		.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
         .logoutSuccessUrl("/user/showlogin?signout")
//		//expected /error handling (for 403 error)
		.and().exceptionHandling().accessDeniedPage("/denied")
//		
//		//add sessionMaxConcurrency
		.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
	}
}
