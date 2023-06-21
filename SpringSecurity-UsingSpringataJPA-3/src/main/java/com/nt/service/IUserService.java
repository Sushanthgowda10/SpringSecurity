package com.nt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nt.entity.UserInfo;

public interface IUserService extends UserDetailsService {
	public String register(UserInfo details);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
