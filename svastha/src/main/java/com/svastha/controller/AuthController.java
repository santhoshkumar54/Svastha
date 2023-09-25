package com.svastha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.config.JwtTokenUtil;
import com.svastha.entity.JwtRequest;
import com.svastha.entity.Users;
import com.svastha.model.UserModel;
import com.svastha.repository.UserRepository;
import com.svastha.service.JwtUserDetailsService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserRepository userDao;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public UserModel createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		Users u = userDao.findByUsername(userDetails.getUsername());

		UserModel model = new UserModel();
		model.setPk1(u.getPk1());
		model.setFirst_name(u.getFirst_name());
		model.setLast_name(u.getLast_name());
		model.setUsername(u.getUsername());
		model.setJwt(token);

		return model;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(t);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
