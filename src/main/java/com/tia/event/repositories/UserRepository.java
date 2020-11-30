package com.tia.event.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tia.event.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
