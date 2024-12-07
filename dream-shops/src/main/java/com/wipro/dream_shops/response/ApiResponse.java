package com.wipro.dream_shops.response;

import java.util.List;

import com.wipro.dream_shops.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse {
	

	//	// created
//	public ApiResponse(String message2, Object object) {
//		// TODO Auto-generated constructor stub
//	}
	private String message;
	private Object data;
	
//	public ApiResponse(String string, Object object) {
//		// TODO Auto-generated constructor stub
//		this.message=string;
//		this.data=object;
//	}
}
