package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true, value = { "hibernateLazyInitializer", "handler" })
@Setter
@Getter
@Entity
@Table(name = "PRODUCT")
@NamedQuery(name = "Product.countIsDelete", query = "SELECT count(p.isDelete) FROM Product p WHERE p.isDelete = false")
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "PRODUCT_NAME")
	private String name;

	@Column(name = "PRICE")
	private Integer price;

	@Column(name = "SIZE")
	private String size;

	@Column(name = "COLOUR")
	private String color;

	@Column(name = "IS_DELETE")
	private Boolean isDelete;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDERS_ID")
	private Orders orders;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PAYMENT_ID")
	private Payment payment;

	public Product() {
		isDelete = false;
	}
}