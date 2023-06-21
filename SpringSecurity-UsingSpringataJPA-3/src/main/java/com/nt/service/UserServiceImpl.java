package com.nt.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.entity.UserInfo;
import com.nt.repository.IUseDetailsRepo;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private IUseDetailsRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> opt= userRepo.findByUname(username);
		if(opt.isEmpty())
			throw new IllegalAccessError("user not found");
		else {
			UserInfo info =opt.get();
			User user =new User(info.getUname(),info.getPwd(),
				info.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));	
		
		return user;
	}
	}

	@Override
	public String register(UserInfo details) {
		details.setPwd(encoder.encode(details.getPwd()));
		System.out.println("----------");
		return userRepo.save(details).getUnid()+"User is Registered";
	}

}
