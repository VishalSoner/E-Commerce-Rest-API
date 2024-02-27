package com.ecommerce.wapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {

	private HttpStatus status;
	private Object message;
	private HttpHeaders header;
	private Exception error;
}
