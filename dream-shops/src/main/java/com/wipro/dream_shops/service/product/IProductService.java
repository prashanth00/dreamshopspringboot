package com.wipro.dream_shops.service.product;

import java.util.List;

import com.wipro.dream_shops.dto.ProductDto;
import com.wipro.dream_shops.model.Product;
import com.wipro.dream_shops.requests.AddProductRequest;
import com.wipro.dream_shops.requests.ProductUpdateRequest;

public interface IProductService {
	Product addProduct(AddProductRequest product);
	Product getProductById(Long id);
	void deleteProductById(Long id);
	Product updateProduct(ProductUpdateRequest request,Long id);
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByBrand(String brand);
	List<Product> getProductsByCategoryAndBrand(String Category,String brand);
	List<Product> getProductsByName(String name);
	List<Product> getProductsByBrandAndName(String category, String name);
	Long countProductsByBrandAndName(String brand,String name);
	ProductDto convertToDto(Product product);
	List<ProductDto> getConvertedProducts(List<Product> products);
}

