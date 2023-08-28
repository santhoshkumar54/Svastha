package com.svastha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.Roles;
import com.svastha.entity.Users;
import com.svastha.repository.RoleRepository;
import com.svastha.repository.UserRepository;

@RestController
public class AdminController {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private RoleRepository roleDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@PostMapping("/addUsers")
	public String addUsers(@RequestBody Users user) {
		try {
			user.setPassword((bcryptEncoder.encode(user.getPassword())));
			userDao.save(user);
			return "saved";

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping(path = "/users")
	public @ResponseBody Iterable<Users> getAllUsers() {

		return userDao.findAll();
	}

	@GetMapping("/addroles")
	public String addRoles() {
		Roles r = new Roles();
		r.setRole("ADMIN");
		roleDao.save(r);
		return "saved";
	}

	@GetMapping(path = "/roles")
	public @ResponseBody Iterable<Roles> getAllRoles() {

		return roleDao.findAll();
	}
}
