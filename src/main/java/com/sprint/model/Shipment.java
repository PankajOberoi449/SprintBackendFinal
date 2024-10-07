package com.sprint.model;


//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Table(name = "shipments")
//@Data
//public class Shipment {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "shipment_id")
//	private int shipmentId;
//
//	@Column(name = "delivery_address", nullable = false, length = 512)
//	private String deliveryAddress;
//
//	@Column(name = "shipment_status", nullable = false, length = 100)
//	private String shipmentStatus;
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "store_id")
//	// @JsonBackReference(value = "store-move")
//	private Store store;
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "customer_id")
//	// @JsonBackReference(value = "customer-move")
//	private Customer customer;
//
//}

//package com.sprint.model;

//

//import jakarta.persistence.CascadeType;

//import jakarta.persistence.Column;

//import jakarta.persistence.Entity;

//import jakarta.persistence.GeneratedValue;

//import jakarta.persistence.GenerationType;

//import jakarta.persistence.Id;

//import jakarta.persistence.JoinColumn;

//import jakarta.persistence.ManyToOne;

//import jakarta.persistence.Table;

//import lombok.Data;

//

//@Entity

//@Table(name = "shipments")

//@Data

//public class Shipment {

//

//	@Id

//	@GeneratedValue(strategy = GenerationType.IDENTITY)

//	@Column(name = "shipment_id")

//	private int shipmentId;

//

//	@Column(name = "delivery_address", nullable = false, length = 512)

//	private String deliveryAddress;

//

//	@Column(name = "shipment_status", nullable = false, length = 100)

//	private String shipmentStatus;

//

//	@ManyToOne(cascade = CascadeType.ALL)

//	@JoinColumn(name = "store_id")

//	// @JsonBackReference(value = "store-move")

//	private Store store;

//

//	@ManyToOne(cascade = CascadeType.ALL)

//	@JoinColumn(name = "customer_id")

//	// @JsonBackReference(value = "customer-move")

//	private Customer customer;

//

//}


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity

@Table(name = "shipments")

@Data

public class Shipment {

  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)

  @Column(name = "shipment_id")

  private int shipmentId;

  @Column(name = "delivery_address", nullable = false, length = 512)

  private String deliveryAddress;

  @Column(name = "shipment_status", nullable = false, length = 100)

  private String shipmentStatus;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Avoiding unnecessary data loading

  @JoinColumn(name = "store_id")

  private Store store;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) // Avoiding unnecessary data loading

  @JoinColumn(name = "customer_id")

  private Customer customer;

public int getShipmentId() {
	return shipmentId;
}

public void setShipmentId(int shipmentId) {
	this.shipmentId = shipmentId;
}

public String getDeliveryAddress() {
	return deliveryAddress;
}

public void setDeliveryAddress(String deliveryAddress) {
	this.deliveryAddress = deliveryAddress;
}

public String getShipmentStatus() {
	return shipmentStatus;
}

public void setShipmentStatus(String shipmentStatus) {
	this.shipmentStatus = shipmentStatus;
}

public Store getStore() {
	return store;
}

public void setStore(Store store) {
	this.store = store;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public Shipment(int shipmentId, String deliveryAddress, String shipmentStatus, Store store, Customer customer) {
	super();
	this.shipmentId = shipmentId;
	this.deliveryAddress = deliveryAddress;
	this.shipmentStatus = shipmentStatus;
	this.store = store;
	this.customer = customer;
}

public Shipment() {
	super();
}
  

}


