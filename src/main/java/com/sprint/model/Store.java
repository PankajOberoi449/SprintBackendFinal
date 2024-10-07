package com.sprint.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stores")
@Data
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private int storeId;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "web_address")
	private String webAddress;

	@Column(name = "physical_address")
	private String physicalAddress;

	@Column(name = "latitude")
	private BigDecimal latitude;

	@Column(name = "longitude")
	private BigDecimal longitude;

//    @Lob // refers large object
//    private Byte logo;

	@Column(name = "logo_mime_type")
	private String logoMimeType;

	@Column(name = "logo_filename")
	private String logoFileName;

	@Column(name = "logo_charset")
	private String logoCharSet;

	@Column(name = "logo_last_updated")
	private Date logoLastUpdated;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getLogoMimeType() {
		return logoMimeType;
	}

	public void setLogoMimeType(String logoMimeType) {
		this.logoMimeType = logoMimeType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getLogoCharSet() {
		return logoCharSet;
	}

	public void setLogoCharSet(String logoCharSet) {
		this.logoCharSet = logoCharSet;
	}

	public Date getLogoLastUpdated() {
		return logoLastUpdated;
	}

	public void setLogoLastUpdated(Date logoLastUpdated) {
		this.logoLastUpdated = logoLastUpdated;
	}

	public Store(int storeId, String storeName, String webAddress, String physicalAddress, BigDecimal latitude,
			BigDecimal longitude, String logoMimeType, String logoFileName, String logoCharSet, Date logoLastUpdated) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.webAddress = webAddress;
		this.physicalAddress = physicalAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.logoMimeType = logoMimeType;
		this.logoFileName = logoFileName;
		this.logoCharSet = logoCharSet;
		this.logoLastUpdated = logoLastUpdated;
	}

	public Store() {
		super();
	}
	
}
