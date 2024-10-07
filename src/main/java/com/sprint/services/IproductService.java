package com.sprint.services;

import java.util.List;

import com.sprint.dto.ProductDto;
import com.sprint.exceptions.list.BadRequestException;
import com.sprint.exceptions.list.ResourceNotFoundException;

public interface IproductService {
	List<ProductDto> getAllProducts();

	String createProduct(ProductDto productDTO) throws BadRequestException;

	String updateProduct(int productId, ProductDto productDto) throws ResourceNotFoundException;

	String deleteProduct(int id) throws ResourceNotFoundException;

	List<ProductDto> findProductsByName(String name) throws ResourceNotFoundException;

	List<ProductDto> findProductsByBrand(String brand) throws ResourceNotFoundException;

	List<ProductDto> findProductsByColor(String color) throws ResourceNotFoundException;

	List<ProductDto> findProductsByPriceRange(double min, double max) throws BadRequestException;

	List<ProductDto> sortProductsByField(String field) throws BadRequestException;

	List<ProductDto> findProductsByCategory(String category) throws ResourceNotFoundException;

	ProductDto findProductsById(int id) throws BadRequestException;

}