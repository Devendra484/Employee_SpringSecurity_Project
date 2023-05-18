package com.springboot.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Users_;

public interface UsersRepository extends JpaRepository<Users_, Long> {

	Optional<Users_> findByUserName(String username);

	boolean existsByEmail(String email);

	Users_ findByEmail(String email);

}
