package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers",listUsers);
		return "user";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = userService.listRole();
		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("listRoles",listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		System.out.println(user);
		return "redirect:/users";
	}
}
