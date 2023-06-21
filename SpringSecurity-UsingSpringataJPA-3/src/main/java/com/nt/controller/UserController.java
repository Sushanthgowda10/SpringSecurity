package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.entity.UserInfo;
import com.nt.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	@GetMapping("/register") // fotrlaunching form
	public String showUserRegistrationPage(@ModelAttribute("userInfo") UserInfo details) {
		return "user_register";
		
		
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("userInfo") UserInfo details) {
		//use Service
		String msg = service.register(details);
		return "user_registered_success";
	}
	@GetMapping("/showlogin")
	public String showLoginPage() {
		return "Custom_login";
	}
}
