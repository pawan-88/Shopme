package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> listAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public List<Role> listRole(){
		return (List<Role>) roleRepository.findAll();
	}

}
