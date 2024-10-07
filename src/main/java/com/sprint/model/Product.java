//package com.product.model;
//
//public class Product {
//
//}
///
package com.sprint.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id") // This should match the referencedColumnName in OrderItem
	private Long productId;

	// @Column(name = "product_name")
	private String name;

	// @Column(name = "unit_price")
	private Double price;

    // @Column(name = "colour")
	private String color;

	private String brand;

	private String size;

	private Integer rating;

	private String category;
	
	@Column(name = "image",columnDefinition = "TEXT")
	private String image;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product(Long productId, String name, Double price, String color, String brand, String size, Integer rating,
			String category, String image) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.color = color;
		this.brand = brand;
		this.size = size;
		this.rating = rating;
		this.category = category;
		this.image = image;
	}

	public Product() {
		super();
	}
	
	
	
	

}
