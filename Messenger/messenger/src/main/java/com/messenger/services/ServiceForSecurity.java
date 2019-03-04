package com.messenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.messenger.services.interfaces.SecurityService;


@Service
public class ServiceForSecurity implements SecurityService {

	@Autowired
	private AuthenticationManager authentificationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public String findLoggedInPhone() {
		Object userDedails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDedails instanceof UserDetails) {
			return (((UserDetails) userDedails).getUsername());
		}
		return null;
	}

	@Override
	public void autoLogin(String phone, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(phone);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
				password, userDetails.getAuthorities());
		authentificationManager.authenticate(authenticationToken);
		if (authenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}

	}

}
