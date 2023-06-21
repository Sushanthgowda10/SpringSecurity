package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserInfo;

public interface IUseDetailsRepo extends JpaRepository<UserInfo,Integer> {
	public Optional<UserInfo> findByUname(String uname);

}
