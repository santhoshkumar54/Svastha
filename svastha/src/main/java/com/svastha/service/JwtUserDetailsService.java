package com.svastha.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.svastha.entity.Users;
import com.svastha.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
//
	@Autowired
	private UserRepository userDao;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users u = userDao.findByUsername(username);
		
		if( u == null )
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(u.getUsername(), u.getPassword(),
				new ArrayList<>());
	
		
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
	}
	
	
}