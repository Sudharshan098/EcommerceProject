package com.ecom.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Models.Cart;
@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
	public Optional<Cart> findByCustomerId(Integer id);
	

}
