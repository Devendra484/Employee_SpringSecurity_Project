package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.Repository.UsersRepository;
import com.springboot.model.Users_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class UsersService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsersRepository usersRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public Users_ addUser(Users_ users) throws Exception {

		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
		return usersRepository.save(users);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users_> users = usersRepository.findByUserName(username);
		return users.map(UserDetailsImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

}
