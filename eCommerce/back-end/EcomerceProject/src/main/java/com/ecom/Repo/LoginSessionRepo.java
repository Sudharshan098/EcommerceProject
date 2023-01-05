package com.ecom.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Models.LoginSession;

@Repository
public interface LoginSessionRepo extends JpaRepository<LoginSession, Integer> {

	public Optional<LoginSession> findByKey(String key);

}
