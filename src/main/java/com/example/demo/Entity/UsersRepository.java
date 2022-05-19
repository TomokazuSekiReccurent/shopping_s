package com.example.demo.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
	Users findByNameAndPass(String name, String pass);
}
