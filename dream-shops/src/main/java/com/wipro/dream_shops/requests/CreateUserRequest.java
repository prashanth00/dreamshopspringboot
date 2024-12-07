package com.wipro.dream_shops.requests;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Data
public class CreateUserRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
