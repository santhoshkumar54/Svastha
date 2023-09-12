package com.svastha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.svastha.entity.Users;
import com.svastha.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
//
	@Autowired
	private UserRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users u = userDao.findByUsername(username);
		if (u == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRoles().getRole()));

		return new User(u.getUsername(), u.getPassword(), authorities);
	}
}