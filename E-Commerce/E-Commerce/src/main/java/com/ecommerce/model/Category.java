package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "CATEGORY_NAME")
	private String name;

	@Column(name = "IS_DELETE")
	private Boolean isDelete;

	@Column(name = "TYPE")
	private String type;

	public Category() {
		isDelete = false;
	}
}
