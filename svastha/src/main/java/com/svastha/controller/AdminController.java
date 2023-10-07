package com.svastha.controller;

import java.util.Optional;

import com.svastha.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PreAuthorize("hasRole('ADMIN')")

	public String addUsers(@RequestBody UserDTO user) {
		try {
			Users newUser = new Users();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setFirst_name(user.getFirst_name());
			newUser.setLast_name(user.getLast_name());
			newUser.setPhone_number(user.getPhone_number());
			newUser.setRoles(roleDao.findByPk1(user.getRole()));
			userDao.save(newUser);
			return "saved";

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping(path = "/users")
    @PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Iterable<Users> getAllUsers() {

		return userDao.findAll();
	}

	@GetMapping(path = "/user")
	public @ResponseBody Optional<Users> getUserbyUserId(@RequestParam Long userId) {

		return userDao.findById(userId);
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
