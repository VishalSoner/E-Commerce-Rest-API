package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ORDERS")
public class Orders extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "DELIVERY_STATUS")
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	@Column(name = "IS_DELETE")
	private Boolean isDelete;

	public enum DeliveryStatus {
		PENDING, DELIVERED, FAILED
	}

	public Orders() {
		isDelete = false;
	}
}
