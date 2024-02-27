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
@Table(name = "PAYMENT")
public class Payment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "CARD_TYPE")
	@Enumerated(EnumType.STRING)
	private CardType cardType;

	@Column(name = "CARD_HOLDER_NAME")
	private String cardHolderName;

	@Column(name = "CARD_NUMBER")
	private String cardNumber;

	@Column(name = "IS_DELETE")
	private Boolean isDelete;

	@Column(name = "BANK_NAME")
	private String bankName;

	@Column(name = "SECURITY_CODE")
	private String securityCode;

	public enum CardType {
		DEBIT, CREDIT
	}

	public Payment() {
		isDelete = false;
	}
}