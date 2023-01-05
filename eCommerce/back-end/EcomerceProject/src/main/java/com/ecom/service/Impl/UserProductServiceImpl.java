package com.ecom.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.criterion.Distinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecom.Models.Cart;
import com.ecom.Models.Category;
import com.ecom.Models.Customer;
import com.ecom.Models.LoginSession;
import com.ecom.Models.Product;
import com.ecom.Repo.CartRepo;
import com.ecom.Repo.CategoryRepo;
import com.ecom.Repo.LoginSessionRepo;
import com.ecom.Repo.ProductRepo;
import com.ecom.customExepciton.CartExecption;
import com.ecom.customExepciton.ProductExecption;
import com.ecom.service.CartService;
import com.ecom.service.ProductService;
import com.ecom.service.UserProductService;
@Service
public class UserProductServiceImpl implements UserProductService {
	
	@Autowired
	private LoginSessionRepo loginSessionRepo;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired 
	private CategoryRepo categoryRepo;
	
	
	
	// method of checking user
		public LoginSession permissionTo(String key) {
		
		Optional<LoginSession> optional= loginSessionRepo.findByKey(key);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


		@Override
		public Cart addProduct(Product product, String key) throws ProductExecption, CartExecption {
			LoginSession result= this.permissionTo(key);
			
			if(result!=null) {
				Optional<Product> prOptional= productRepo.findById(product.getProductId());
				
				product=prOptional.get();
				if(prOptional.isEmpty()) {
					throw new ProductExecption("Please Do not chagne any date of product");
				}
				Optional<Cart> caOptional= cartRepo.findByCustomerId(result.getCustomerId());
				if(caOptional.isEmpty()) {
					Cart cart=new Cart();
					cart.setCustomerId(result.getCustomerId());
					cart.setTotalItem(1);
					cart.setTotalPrice(+product.getPrice());
					List<Product> list=cart.getProducts();
					product.setQuantity(1);
					list.add(product);
					cart.setProducts(list);
					System.out.println(cart);
					return cartRepo.save(cart);
				}
				else {
					
					Cart cart = caOptional.get();
					Boolean flag=false;
					List<Product> list=cart.getProducts();
					for(Product i:list) {
						if(i.getProductId()==product.getProductId()) {
							cart.setTotalItem(cart.getTotalItem()+1);
							cart.setTotalPrice(cart.getTotalPrice()+product.getPrice());
							i.setQuantity(i.getQuantity()+1);
							flag=true;
						}
					}
					if(flag) {
						return cartRepo.save(cart);
					}
					else {
						cart.setTotalItem(cart.getTotalItem()+1);
						cart.setTotalPrice(cart.getTotalPrice()+product.getPrice());
						product.setQuantity(1);
						list.add(product);
						
						return cartRepo.save(cart);

					}
					
//					return cart;
				}
				
			}
			else {
				throw new ProductExecption("You are Not login");
			}
			
		}




		@Override
		public Cart deleteProduct(Integer productId, String key) throws ProductExecption,CartExecption {
			LoginSession result= this.permissionTo(key);
			
			if(result!=null) {
				Optional<Cart> caOptional= cartRepo.findByCustomerId(result.getCustomerId());
				if(caOptional.isEmpty()) {
					throw new CartExecption("Please Before adding something first create your cart");
					
				}
				else {
					
					Optional<Product> prOptional= productRepo.findById(productId);
					if(prOptional.isPresent()) {
						Cart cart=caOptional.get();
						
						List<Product> list=cart.getProducts();
						int count=0;
						for(Product i:list) {
							
							if(i.getProductId()==prOptional.get().getProductId()) {
								cart.setTotalPrice(cart.getTotalPrice()-prOptional.get().getPrice());
								cart.setTotalItem(cart.getTotalItem()-1);
								if(i.getQuantity()>1) {
									i.setQuantity(i.getQuantity()-1);
									
								}else {
									
									list.remove(count);
									break;
								}
								
							}
							count++;
						}
						return cartRepo.save(cart);
					}
					else {
						throw new ProductExecption("Please enter valid product id");
					}
					
				}
				
			}
			else {
				throw new ProductExecption("You are Not login");
			}
			
		}



		@Override
		public List<Product> getAllProduct() throws ProductExecption {
			List<Product> list=productRepo.findAll();
			for(Product i:list) {
				String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(i.getImage()).toUriString();

				i.setImage(download);
			}
         return list;
			
		}



		@Override
		public Product findProductById(Integer id) throws ProductExecption {
			Optional<Product> prOptional= productRepo.findById(id);
			if(prOptional.isPresent()) {
				Product product=prOptional.get();
				String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(product.getImage()).toUriString();
				product.setImage(download);
				return product;
			}
			throw new ProductExecption("No data found of this id");
		}




		@Override
		public List<Product> findProductByName(String name) throws ProductExecption {
			List<Product> list= productRepo.findByProductName(name);
			for(Product i:list) {
				String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(i.getImage()).toUriString();
				i.setImage(download);
			}
			return list;
			
		}


		@Override
		public List<Product> sortByRating(Integer startingPoint, Integer endingPoint) throws ProductExecption {
			List<Product> list= productRepo.findByproductrating(startingPoint, endingPoint);
			for(Product i:list) {
				String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(i.getImage()).toUriString();
				i.setImage(download);
			}
			return list;
			
		}


		@Override
		public Set<Product> searchByCatgory(String categoryName) throws ProductExecption {
			Optional<Category> cat= categoryRepo.findByCategoryName(categoryName);
			Set<Product> list=cat.get().getProducts();
			for(Product i:list) {
				String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(i.getImage()).toUriString();
				i.setImage(download);
			}
			return list;
		}

}
