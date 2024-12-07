package com.wipro.dream_shops.service.product;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.wipro.dream_shops.dto.ImageDto;
import com.wipro.dream_shops.dto.ProductDto;
import com.wipro.dream_shops.exceptions.ProductNotFoundException;
import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.Category;
import com.wipro.dream_shops.model.Image;
import com.wipro.dream_shops.model.Product;
import com.wipro.dream_shops.repository.CategoryRepository;
import com.wipro.dream_shops.repository.ImageRepository;
import com.wipro.dream_shops.repository.ProductRepository;
import com.wipro.dream_shops.requests.AddProductRequest;
import com.wipro.dream_shops.requests.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	private final ImageRepository imageRepository;
	
	@Override
	public Product addProduct(AddProductRequest request) {
		// TODO Auto-generated method stub
		// if category is found in DB
		//If yes, set is as the new product category
		//if no, then save it as a new category
		// The set as the new product category
		
		Category category=Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName())).orElseGet(()->{
			Category newCategory=new Category(request.getCategory().getName());
			return categoryRepository.save(newCategory);
		});
		request.setCategory(category);
		return productRepository.save(createProduct(request,category));
	}

	private Product createProduct(AddProductRequest request,Category category) {
		return new Product(
				request.getName(),
				request.getBrand(),
				request.getPrice(),
				request.getInventory(),
				request.getDescription(),
				category
		);
	}
	
	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not Found"));
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		productRepository.findById(id).ifPresentOrElse(productRepository::delete,
				()->{throw new ResourceNotFoundException("Product not Found");
				}
		);
	}

	@Override
	public Product updateProduct(ProductUpdateRequest request, Long productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId)
				.map(existingProduct->updateExistingProduct(existingProduct,request))
				.map(productRepository::save)
				.orElseThrow(()->new ResourceNotFoundException("Product not found"));
	}
	
	private Product updateExistingProduct(Product existingProduct,ProductUpdateRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setBrand(request.getDescription());
		
		Category category=categoryRepository.findByName(request.getCategory().getName());
		existingProduct.setCategory(category);
		return existingProduct;
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
		return productRepository.findByCategoryNameAndBrand(Category, brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
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
	
	@Override
	public List<ProductDto> getConvertedProducts(List<Product> products){
		return products.stream().map(this::convertToDto).toList();
	}
	
	@Override
	public ProductDto convertToDto(Product product) {
		ProductDto productDto=modelMapper.map(product, ProductDto.class);
		List<Image> images=imageRepository.findByProductId(product.getId());
		List<ImageDto> imageDtos = images.stream()
				.map(image->modelMapper.map(image, ImageDto.class))
				.toList();
		productDto.setImages(imageDtos);
		return productDto;
	}

}
