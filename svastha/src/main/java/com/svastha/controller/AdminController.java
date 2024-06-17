package com.svastha.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.svastha.util.UserDTO;

@RestController
public class AdminController {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private RoleRepository roleDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	 @Autowired
	 private JavaMailSender mailSender;

	@PostMapping("/addUsers")
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
			StringBuilder content = new StringBuilder();
			content.append("Hello ").append(user.getFirst_name()).append(",\n");
			content.append("         Your account has been created successfully in Svastha application.\n");
			content.append("\n Your user name : ").append(user.getUsername());
			content.append("\n Your password : " ).append(user.getPassword());
			content.append("\n You can download your Android Application here : ");
			content.append("https://app.svasthaecoharvest.com/svastha/downloadapk");
			sendEmail(user.getUsername(), "Svastha account created",content.toString());
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
	
	public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
