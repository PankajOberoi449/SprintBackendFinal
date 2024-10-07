package com.sprint.services;
//
//import com.sprint.dao.ProductRepository;
//import com.sprint.dto.ProductDto;
//import com.sprint.exceptions.list.BadRequestException;
//import com.sprint.exceptions.list.ResourceNotFoundException;
//import com.sprint.model.Product;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.anyDouble;
//import static org.mockito.Mockito.when;
//
//public class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productDao;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private ProductService productService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllProducts() {
//        List<Product> products = new ArrayList<>();
//        Product product = new Product();
//        products.add(product);
//
//        ProductDto productDto = new ProductDto();
//        when(productDao.findAll()).thenReturn(products);
//        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
//
//        List<ProductDto> result = productService.getAllProducts();
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(productDto, result.get(0));
//    }
//
//    @Test
//    public void testCreateProduct_Success() throws BadRequestException {
//        ProductDto productDto = new ProductDto();
//        productDto.setName("Test Product");
//
//        Product product = new Product();
//        product.setProductId((long) 1); // Assuming you have this method in your model
//
//        Product savedProduct = new Product();
//        savedProduct.setProductId((long) 1);
//
//        when(modelMapper.map(productDto, Product.class)).thenReturn(product);
//        when(productDao.save(product)).thenReturn(savedProduct);
//
//        String result = productService.createProduct(productDto);
//        assertEquals("Product created successfully with ID: 1", result);
//    }
//
//    @Test
//    public void testCreateProduct_Failure() {
//        ProductDto productDto = new ProductDto();
//        productDto.setName("");
//
//        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
//            productService.createProduct(productDto);
//        });
//
//        assertEquals("Invalid request. Please provide valid product data for creation.", thrown.getMessage());
//    }
//
//    @Test
//    public void testUpdateProduct_Success() throws ResourceNotFoundException {
//        ProductDto productDto = new ProductDto();
//        productDto.setName("Updated Product");
//        productDto.setPrice(100.0);
//
//        Product existingProduct = new Product();
//        existingProduct.setProductId((long) 1);
//
//        Product updatedProduct = new Product();
//        updatedProduct.setProductId((long) 1);
//
//        when(productDao.findById(anyInt())).thenReturn(Optional.of(existingProduct));
//        when(productDao.save(any(Product.class))).thenReturn(updatedProduct);
//
//        String result = productService.updateProduct(1, productDto);
//        assertEquals("Product updated successfully with ID: 1", result);
//    }
//
//    @Test
//    public void testUpdateProduct_Failure() {
//        ProductDto productDto = new ProductDto();
//        productDto.setName("Updated Product");
//
//        when(productDao.findById(anyInt())).thenReturn(Optional.empty());
//
//        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
//            productService.updateProduct(1, productDto);
//        });
//
//        assertEquals("Product with ID 1 not found", thrown.getMessage());
//    }
//
//    @Test
//    public void testDeleteProduct_Success() throws ResourceNotFoundException {
//        when(productDao.existsById(anyInt())).thenReturn(true);
//
//        String result = productService.deleteProduct(1);
//        assertEquals("Product deleted successfully with ID: 1", result);
//    }
//
//	/*
//	 * @Test public void testDeleteProduct_Failure() {
//	 * when(productDao.existsById(anyInt())).thenReturn(false);
//	 * 
//	 * ResourceNotFoundException thrown =
//	 * assertThrows(ResourceNotFoundException.class, () -> {
//	 * productService.deleteProduct(1); });
//	 * 
//	 * assertEquals("Product with the specified ID not found for deletion.",
//	 * thrown.getMessage()); }
//	 */
//
//    @Test
//    public void testFindProductsByName_Success() throws ResourceNotFoundException {
//        List<Product> products = new ArrayList<>();
//        Product product = new Product();
//        products.add(product);
//
//        ProductDto productDto = new ProductDto();
//        when(productDao.findByName(anyString())).thenReturn(products);
//        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
//
//        List<ProductDto> result = productService.findProductsByName("Test Product");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(productDto, result.get(0));
//    }
//
//    @Test
//    public void testFindProductsByName_Failure() {
//        when(productDao.findByName(anyString())).thenReturn(new ArrayList<>());
//
//        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
//            productService.findProductsByName("Test Product");
//        });
//
//        assertEquals("Product(s) with the specified name not found.", thrown.getMessage());
//    }
//
//    @Test
//    public void testFindProductsByBrand_Success() throws ResourceNotFoundException {
//        List<Product> products = new ArrayList<>();
//        Product product = new Product();
//        products.add(product);
//
//        ProductDto productDto = new ProductDto();
//        when(productDao.findByBrand(anyString())).thenReturn(products);
//        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
//
//        List<ProductDto> result = productService.findProductsByBrand("Test Brand");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(productDto, result.get(0));
//    }
//
//    @Test
//    public void testFindProductsByBrand_Failure() {
//        when(productDao.findByBrand(anyString())).thenReturn(new ArrayList<>());
//
//        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
//            productService.findProductsByBrand("Test Brand");
//        });
//
//        assertEquals("Products with the specified brand not found.", thrown.getMessage());
//    }
//
//    @Test
//    public void testFindProductsByColor_Success() throws ResourceNotFoundException {
//        List<Product> products = new ArrayList<>();
//        Product product = new Product();
//        products.add(product);
//
//        ProductDto productDto = new ProductDto();
//        when(productDao.findByColor(anyString())).thenReturn(products);
//        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
//
//        List<ProductDto> result = productService.findProductsByColor("Test Color");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(productDto, result.get(0));
//    }
//
//    @Test
//    public void testFindProductsByColor_Failure() {
//        when(productDao.findByColor(anyString())).thenReturn(new ArrayList<>());
//
//        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
//            productService.findProductsByColor("Test Color");
//        });
//
//        assertEquals("Products with the specified color not found.", thrown.getMessage());
//    }
//
//    @Test
//    public void testFindProductsByPriceRange_Success() throws BadRequestException {
//        List<Product> products = new ArrayList<>();
//        Product product = new Product();
//        products.add(product);
//
//        ProductDto productDto = new ProductDto();
//        when(productDao.findByPriceBetween(anyDouble(), anyDouble())).thenReturn(products);
//        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
//
//        List<ProductDto> result = productService.findProductsByPriceRange(10.0, 100.0);
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(productDto, result.get(0));
//    }
//
//    @Test
//    public void testFindProductsByPriceRange_Failure() {
//        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
//            productService.findProductsByPriceRange(-10.0, 100.0);
//        });
//
//        assertEquals("Invalid request. Please provide valid minimum and maximum unit prices.", thrown.getMessage());
//    }
//
//    @Test
//    public void testSortProductsByField_Success() throws BadRequestException {
//        List<Product> products = new ArrayList<>();
//        Product product1 = new Product();
//        product1.setPrice(50.0);
//        Product product2 = new Product();
//        product2.setPrice(100.0);
//        products.add(product1);
//        products.add(product2);
//
//        ProductDto productDto = new ProductDto();
//        when(productDao.findAll()).thenReturn(products);
//        when(modelMapper.map(product1, ProductDto.class)).thenReturn(productDto);
//        when(modelMapper.map(product2, ProductDto.class)).thenReturn(productDto);
//
//        List<ProductDto> result = productService.sortProductsByField("price");
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals(productDto, result.get(0));
//    }
//
//    @Test
//    public void testSortProductsByField_Failure() {
//        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
//            productService.sortProductsByField("invalidField");
//        });
//
//        assertEquals("Invalid request. Please provide a valid field for sorting.", thrown.getMessage());
//    }
//
//    @Test
//    public void testFindProductsByCategory_Success() throws ResourceNotFoundException {
//        List<Product> products = new ArrayList<>();
//        Product product = new Product();
//        products.add(product);
//
//        ProductDto productDto = new ProductDto();
//        when(productDao.findByCategory(anyString())).thenReturn(products);
//        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
//
//        List<ProductDto> result = productService.findProductsByCategory("Test Category");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(productDto, result.get(0));
//    }
//
//    @Test
//    public void testFindProductsByCategory_Failure() {
//        when(productDao.findByCategory(anyString())).thenReturn(new ArrayList<>());
//
//        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
//            productService.findProductsByCategory("Test Category");
//        });
//
//        assertEquals("Products with the specified category not found.", thrown.getMessage());
//    }
//}


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.sprint.dao.ProductRepository;
import com.sprint.dto.ProductDto;
import com.sprint.exceptions.list.BadRequestException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.model.Product;
import com.sprint.services.ProductService;

public class ProductServiceTest {

    @Mock
    private ProductRepository productDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Product1");

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Product2");

        List<Product> products = List.of(product1, product2);

        ProductDto dto1 = new ProductDto();
        dto1.setId(1);
        dto1.setName("Product1");

        ProductDto dto2 = new ProductDto();
        dto2.setId(2);
        dto2.setName("Product2");

        List<ProductDto> dtos = List.of(dto1, dto2);

        when(productDao.findAll()).thenReturn(products);
        when(modelMapper.map(product1, ProductDto.class)).thenReturn(dto1);
        when(modelMapper.map(product2, ProductDto.class)).thenReturn(dto2);

        List<ProductDto> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(dtos, result);
    }

    @Test
    public void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("NewProduct");

        Product product = new Product();
        product.setName("NewProduct");

        when(modelMapper.map(productDto, Product.class)).thenReturn(product);

        String result = productService.createProduct(productDto);

        verify(productDao).save(product);
        assertEquals("Product created successfully!", result);
    }

    @Test
    public void testUpdateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("UpdatedProduct");

        Product existingProduct = new Product();
        existingProduct.setProductId(1L);
        existingProduct.setName("OldProduct");

        Product updatedProduct = new Product();
        updatedProduct.setProductId(1L);
        updatedProduct.setName("UpdatedProduct");

        when(productDao.findById(1)).thenReturn(java.util.Optional.of(existingProduct));
        when(modelMapper.map(productDto, Product.class)).thenReturn(updatedProduct);

        String result = productService.updateProduct(1, productDto);

        verify(productDao).save(updatedProduct);
        assertEquals("Product updated successfully!", result);
    }

//    @Test
//    public void testDeleteProduct() throws ResourceNotFoundException {
//        when(productDao.existsById(1)).thenReturn(true);
//
//        String result = productService.deleteProduct(0);
//
//        verify(productDao).deleteById(1);
//        assertEquals("Product deleted successfully with ID: "+1, result);
//    }

    @Test
    public void testDeleteProductNotFound() {
        when(productDao.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.deleteProduct(1);
        });
    }

    @Test
    public void testFindProductsById() throws BadRequestException {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Product1");

        ProductDto dto = new ProductDto();
        dto.setId(1);
        dto.setName("Product1");

        when(productDao.findById(1)).thenReturn(java.util.Optional.of(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(dto);

        ProductDto result = productService.findProductsById(1);

        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    public void testFindProductsByIdNotFound() {
        when(productDao.findById(1)).thenReturn(java.util.Optional.empty());

        assertThrows(BadRequestException.class, () -> {
            productService.findProductsById(1);
        });
    }

    @Test
    public void testFindProductsByName() throws ResourceNotFoundException {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Product1");

        ProductDto dto = new ProductDto();
        dto.setId(1);
        dto.setName("Product1");

        List<Product> products = List.of(product);
        List<ProductDto> dtos = List.of(dto);

        when(productDao.findByName("Product1")).thenReturn(products);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(dto);

        List<ProductDto> result = productService.findProductsByName("Product1");

        assertNotNull(result);
        assertEquals(dtos, result);
    }

    @Test
    public void testFindProductsByNameNotFound() {
        when(productDao.findByName("Product1")).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findProductsByName("Product1");
        });
    }

    @Test
    public void testFindProductsByBrand() throws ResourceNotFoundException {
        Product product = new Product();
        product.setProductId(1L);
        product.setBrand("Brand1");

        ProductDto dto = new ProductDto();
        dto.setId(1);
        dto.setBrand("Brand1");

        List<Product> products = List.of(product);
        List<ProductDto> dtos = List.of(dto);

        when(productDao.findByBrand("Brand1")).thenReturn(products);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(dto);

        List<ProductDto> result = productService.findProductsByBrand("Brand1");

        assertNotNull(result);
        assertEquals(dtos, result);
    }

    @Test
    public void testFindProductsByBrandNotFound() {
        when(productDao.findByBrand("Brand1")).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findProductsByBrand("Brand1");
        });
    }

    @Test
    public void testFindProductsByColor() throws ResourceNotFoundException {
        Product product = new Product();
        product.setProductId(1L);
        product.setColor("Red");

        ProductDto dto = new ProductDto();
        dto.setId(1);
        dto.setColor("Red");

        List<Product> products = List.of(product);
        List<ProductDto> dtos = List.of(dto);

        when(productDao.findByColor("Red")).thenReturn(products);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(dto);

        List<ProductDto> result = productService.findProductsByColor("Red");

        assertNotNull(result);
        assertEquals(dtos, result);
    }

    @Test
    public void testFindProductsByColorNotFound() {
        when(productDao.findByColor("Red")).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findProductsByColor("Red");
        });
    }

    @Test
    public void testFindProductsByPriceRange() throws BadRequestException {
        Product product = new Product();
        product.setProductId(1L);
        product.setPrice(100.0);

        ProductDto dto = new ProductDto();
        dto.setId(1);
        dto.setPrice(100.0);

        List<Product> products = List.of(product);
        List<ProductDto> dtos = List.of(dto);

        when(productDao.findByPriceBetween(50.0, 150.0)).thenReturn(products);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(dto);

        List<ProductDto> result = productService.findProductsByPriceRange(50.0, 150.0);

        assertNotNull(result);
        assertEquals(dtos, result);
    }

    @Test
    public void testFindProductsByPriceRangeInvalid() {
        assertThrows(BadRequestException.class, () -> {
            productService.findProductsByPriceRange(-10.0, 150.0);
        });
    }



//    @Test
//    public void testSortProductsByField() throws BadRequestException {
//        // Given
//        Product product1 = new Product();
//        product1.setProductId(1L);
//        product1.setPrice(100.0);
//        product1.setName("Product1");
//
//        Product product2 = new Product();
//        product2.setProductId(2L);
//        product2.setPrice(50.0);
//        product2.setName("Product2");
//
//        ProductDto dto1 = new ProductDto();
//        dto1.setId(1);
//        dto1.setPrice(100.0);
//        dto1.setName("Product1");
//
//        ProductDto dto2 = new ProductDto();
//        dto2.setId(2);
//        dto2.setPrice(50.0);
//        dto2.setName("Product2");
//
//        List<Product> products = List.of(product1, product2);
//        List<ProductDto> expectedDtos = List.of(dto2, dto1); // Expected order after sorting
//
//        // When
//        when(productDao.findAll()).thenReturn(products);
//        when(modelMapper.map(product1, ProductDto.class)).thenReturn(dto1);
//        when(modelMapper.map(product2, ProductDto.class)).thenReturn(dto2);
//
//        List<ProductDto> result = productService.sortProductsByField("price");
//
//        // Then
//        assertNotNull(result);
//        assertEquals(expectedDtos, result);
//    }


    @Test
    public void testFindProductsByCategory() throws ResourceNotFoundException {
        Product product = new Product();
        product.setProductId(1L);
        product.setCategory("Category1");

        ProductDto dto = new ProductDto();
        dto.setId(1);
        dto.setCategory("Category1");

        List<Product> products = List.of(product);
        List<ProductDto> dtos = List.of(dto);

        when(productDao.findByCategory("Category1")).thenReturn(products);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(dto);

        List<ProductDto> result = productService.findProductsByCategory("Category1");

        assertNotNull(result);
        assertEquals(dtos, result);
    }

    @Test
    public void testFindProductsByCategoryNotFound() {
        when(productDao.findByCategory("Category1")).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findProductsByCategory("Category1");
        });
    }
}

