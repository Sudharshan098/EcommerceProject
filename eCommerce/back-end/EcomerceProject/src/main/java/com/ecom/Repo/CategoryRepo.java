package com.ecom.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Models.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	public Optional<Category> findByCategoryName(String name);
}
