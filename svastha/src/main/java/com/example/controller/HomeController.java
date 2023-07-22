package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Roles;
import com.example.entity.Users;
import com.example.model.UserModel;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.JwtUserDetailsService;

@RestController
public class HomeController {

	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private RoleRepository roleDao;
	
	@Autowired
	private JwtUserDetailsService userService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@GetMapping("/hello")
	public String index() {
		return "Hello Deepan";
	}
	
	@PostMapping("/addUsers")
	public String addUsers( @RequestBody Users user ) 
	{
		try
		{
			user.setPassword((bcryptEncoder.encode(user.getPassword())));
			userDao.save(user);
			return "saved";

			}
			catch(Exception e){
				return e.getMessage();
			}
	}
	
	@GetMapping(path="/users")
    public @ResponseBody Iterable<Users> getAllUsers() {
      
        return userDao.findAll();
    }
	
	@GetMapping("/addroles")
	public String addRoles() 
	{
		Roles r = new Roles();
		r.setRole("ADMIN");
		roleDao.save(r);
		return "saved";
	}
	
	@GetMapping(path="/roles")
    public @ResponseBody Iterable<Roles> getAllRoles() {
      
        return roleDao.findAll();
	}
	
	
}
