package com.wipro.dream_shops.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.dream_shops.exceptions.ProductNotFoundException;
import com.wipro.dream_shops.model.Product;
import com.wipro.dream_shops.model.repository.ProductRepository;
import com.wipro.dream_shops.requests.AddProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

	private final ProductRepository productRepository;
	@Override
	public Product addProduct(AddProductRequest product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not Found"));
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		productRepository.findById(id).ifPresentOrElse(productRepository::delete,
				()->{throw new ProductNotFoundException("Product not Found");
				}
		);
	}

	@Override
	public void updateProduct(Product product, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		// TODO Auto-generated method stub
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String Category, String brand) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryAndBrand(Category, brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name));
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return productRepository.findByBrandAndName(brand,name);
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return productRepository.countByBrandAndName(brand,name);
	}

}
