package com.ecommerce.model;

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

@Setter
@Getter
@Entity
@Table(name = "CUSTOMER")
@NamedQuery(name = "Customer.countIsDelete", query = "SELECT count(c.isDelete) FROM Customer c WHERE c.isDelete = false")
public class Customer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "CUSTOMER_NAME")
	private String name;

	@Column(name = "IS_DELETE")
	private Boolean isDelete;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	public Customer() {
		isDelete = false;
	}
}
