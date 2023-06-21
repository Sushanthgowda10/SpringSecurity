package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityUsingJdbcAuthentication02Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityUsingJdbcAuthentication02Application.class, args);
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		String pwd1 = encode.encode("softtek@2021");
		String pwd2 = encode.encode("softtek@2023");
		System.err.println(pwd1+"--------"+pwd2);
	}

}
