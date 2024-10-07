package com.sprint.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.dao.ProductRepository;
import com.sprint.dto.ProductDto;
import com.sprint.exceptions.list.BadRequestException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.model.Product;

@Service

public class ProductService implements IproductService {

	@Autowired

	private ProductRepository productDao;

	@Autowired

	private ModelMapper modelMapper;

	@Override

	public List<ProductDto> getAllProducts() {

		List<Product> products = productDao.findAll();

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {

			ProductDto productDto = modelMapper.map(product, ProductDto.class);

			productDtos.add(productDto);

		}

		return productDtos;

	}

	public String createProduct(ProductDto productDto) {

		Product product = new Product();

		product.setName(productDto.getName());

		product.setPrice(productDto.getPrice());

		product.setColor(productDto.getColor());

		product.setBrand(productDto.getBrand());

		product.setSize(productDto.getSize());

		product.setRating(productDto.getRating());

		product.setCategory(productDto.getCategory());

		product.setImage(productDto.getImage()); // Set the Base64 image

		productDao.save(product);

		return "Product created successfully!";

	}

	public String updateProduct(int id, ProductDto productDto) {

		Product product = productDao.findById(id)

				.orElseThrow(() -> new RuntimeException("Product not found"));

		product.setName(productDto.getName());

		product.setPrice(productDto.getPrice());

		product.setColor(productDto.getColor());

		product.setBrand(productDto.getBrand());

		product.setSize(productDto.getSize());

		product.setRating(productDto.getRating());

		product.setCategory(productDto.getCategory());

		product.setImage(productDto.getImage()); // Update the Base64 image

		productDao.save(product);

		return "Product updated successfully!";

	}

	@Override

	public String deleteProduct(int productId) throws ResourceNotFoundException {

		if (!productDao.existsById(productId)) {

			throw new ResourceNotFoundException("Product with the specified ID not found for deletion. ");

		}

		productDao.deleteById(productId);

		return "Product deleted successfully with ID: " + productId;

	}

	// find by id

	@Override

	public ProductDto findProductsById(int productId) throws BadRequestException {

		Product product = productDao.findById(productId)

				.orElseThrow(() -> new BadRequestException("Invalid request. Please provide a valid Id."));

		return modelMapper.map(product, ProductDto.class);

	}

	@Override

	public List<ProductDto> findProductsByName(String name) throws ResourceNotFoundException {

		List<Product> products = productDao.findByName(name);

		if (products.isEmpty()) {

			throw new ResourceNotFoundException("Product(s) with the specified name not found.");

		}

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {

			ProductDto productDto = modelMapper.map(product, ProductDto.class);

			productDtos.add(productDto);

		}

		return productDtos;

	}

	@Override

	public List<ProductDto> findProductsByBrand(String brand) throws ResourceNotFoundException {

		List<Product> products = productDao.findByBrand(brand);

		if (products.isEmpty()) {

			throw new ResourceNotFoundException("Products with the specified brand not found.");

		}

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {

			ProductDto productDto = modelMapper.map(product, ProductDto.class);

			productDtos.add(productDto);

		}

		return productDtos;

	}

	@Override

	public List<ProductDto> findProductsByColor(String color) throws ResourceNotFoundException {

		List<Product> products = productDao.findByColor(color);

		if (products.isEmpty()) {

			throw new ResourceNotFoundException("Products with the specified color not found.");

		}

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {

			ProductDto productDto = modelMapper.map(product, ProductDto.class);

			productDtos.add(productDto);

		}

		return productDtos;

	}

	@Override

	public List<ProductDto> findProductsByPriceRange(double min, double max) throws BadRequestException {

		if (min < 0 || max < 0 || min > max) {

			throw new BadRequestException("Invalid request. Please provide valid minimum and maximum unit prices.");

		}

		List<Product> products = productDao.findByPriceBetween(min, max);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {

			ProductDto productDto = modelMapper.map(product, ProductDto.class);

			productDtos.add(productDto);

		}

		return productDtos;

	}

	@Override

	public List<ProductDto> sortProductsByField(String field) throws BadRequestException {

		List<Product> products = productDao.findAll();

		if (products.isEmpty()) {

			throw new BadRequestException("Invalid request. Please provide a valid field for sorting.");

		}

		switch (field.toLowerCase()) {

		case "price":

			products.sort(Comparator.comparing(Product::getPrice, Comparator.nullsLast(Comparator.naturalOrder())));

			break;

		case "name":

			products.sort(Comparator.comparing(Product::getName));

			break;

		case "brand":

			products.sort(Comparator.comparing(Product::getBrand));

			break;

		case "color":

			products.sort(Comparator.comparing(Product::getColor));

			break;

		default:

			throw new BadRequestException("Invalid request. Please provide a valid field for sorting.");

		}

		return products.stream().map(product -> modelMapper.map(product, ProductDto.class))

				.collect(Collectors.toList());

	}

	@Override

	public List<ProductDto> findProductsByCategory(String category) throws ResourceNotFoundException {

		List<Product> products = productDao.findByCategory(category);

		if (products.isEmpty()) {

			throw new ResourceNotFoundException("Products with the specified category not found.");

		}

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {

			ProductDto productDto = modelMapper.map(product, ProductDto.class);

			productDtos.add(productDto);

		}

		return productDtos;

	}

}
