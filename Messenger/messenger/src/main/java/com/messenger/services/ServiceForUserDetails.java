package com.messenger.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.messenger.entities.Role;
import com.messenger.entities.User;
import com.messenger.reposiroties.UserRepository;

@Service
public class ServiceForUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String Phone) throws UsernameNotFoundException {
		User user = userRepository.findByPhone(Phone);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRole()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(),
				grantedAuthorities);
	}
}