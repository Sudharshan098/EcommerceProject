package com.ecom.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.Models.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	public List<Product> findByProductName(String name);
	@Query("from Product p where p.rating=?1 AND p.rating=?2")
	public List<Product> findByproductrating(Integer startingPoint,Integer endingPoint);
	
}
