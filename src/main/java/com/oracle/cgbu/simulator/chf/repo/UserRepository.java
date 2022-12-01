package com.oracle.cgbu.simulator.chf.repo;

import org.springframework.data.repository.CrudRepository;

import com.oracle.cgbu.simulator.chf.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findBySupi(String supi);
}
