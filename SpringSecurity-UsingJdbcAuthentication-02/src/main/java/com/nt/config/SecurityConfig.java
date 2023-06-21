package com.nt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERS WHERE UNAME=?")
				.authoritiesByUsernameQuery("SELECT Uname,role FROM user_role WHERE Uname=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Authorized request

		http.authorizeHttpRequests().antMatchers("/").permitAll()// NO authentication and no authorized
				.antMatchers("/offers").authenticated() // only authentication
				.antMatchers("/balance").hasAnyAuthority("CUSTOMER", "MANAGER")
				.antMatchers("/loanApproval").hasAuthority("MANAGER").anyRequest().authenticated()
				

				// Specific authentication mode
//	     .and().httpBasic();

				.and().formLogin().and().rememberMe().and().logout()

				.logoutRequestMatcher(new AntPathRequestMatcher("/signOut"))

				// expected /error handling (for 403 error)
				.and().exceptionHandling().accessDeniedPage("/denied")

				// add sessionMaxConcurrency
				.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
	}

}
