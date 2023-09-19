package com.shopme.admin.user;


import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class, 1); 
		User user = new User("pawansanap8619@gmail.com","pawan-88","Pawan","Sanap");
		user.addRole(roleAdmin);
		
		User saveUser = userRepository.save(user);
		assertThat(saveUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testCreateWithNewUserWithTwoRoles() {
		User roleUser = new User("shubham@gmail.com","shubham2023","Shubham","Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		roleUser.addRole(roleEditor);
		roleUser.addRole(roleAssistant);
		
		User savedUser = userRepository.save(roleUser);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListOfAllUsers() {
	Iterable<User> listUsers =	userRepository.findAll();
	listUsers.forEach(user->
	System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User getUser = userRepository.findById(1).get();
		System.out.println("Get The User: "+getUser);
		assertThat(getUser).isNotNull();	
	}
	
	@Test
	public void testUpdateUserDetails() {
		User updateUser = userRepository.findById(2).get();
		updateUser.setEnabled(true);
		updateUser.setEmail("shubham@gmail.com");
		
		userRepository.save(updateUser);
		
	}
	
	@Test
	public void testUpdateUserRoles() {
		User updateUserRoles = userRepository.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesPerson = new Role(2);
		
		updateUserRoles.getRoles().remove(roleEditor);
		updateUserRoles.addRole(roleSalesPerson);
		
		userRepository.save(updateUserRoles);
	}
	
	@Test
	public void testDeleteUser() {
		User deleteUser = userRepository.findById(18).get();
		userRepository.delete(deleteUser);
	}
	
	
	
	

}
