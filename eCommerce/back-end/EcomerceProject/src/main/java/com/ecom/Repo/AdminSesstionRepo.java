package com.ecom.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Models.AdminSession;

@Repository
public interface AdminSesstionRepo extends JpaRepository<AdminSession, Integer>{
	public Optional<AdminSession> findByKey(String key);
}
