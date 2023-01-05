package com.ecom.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Models.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
	
	public Optional<Admin> findByEmailAndPassword(String email,String password);
	
}
