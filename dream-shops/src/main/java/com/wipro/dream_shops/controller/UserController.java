package com.wipro.dream_shops.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.dream_shops.dto.UserDto;
import com.wipro.dream_shops.exceptions.AlreadyExistsException;
import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.User;
import com.wipro.dream_shops.requests.CreateUserRequest;
import com.wipro.dream_shops.requests.UserUpdateRequest;
import com.wipro.dream_shops.response.ApiResponse;
import com.wipro.dream_shops.service.product.IProductService;
import com.wipro.dream_shops.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
	private final IUserService userService;

	@GetMapping("/{userId}/user")
	public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
		try {
			User user = userService.getUserById(userId);
			UserDto userDto=userService.convertUserToDto(user);
			return ResponseEntity.ok(new ApiResponse("success", userDto));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request){
		try {
			User user=userService.createUser(request);
			UserDto userDto=userService.convertUserToDto(user);
			return ResponseEntity.ok(new ApiResponse("Create User Success!",userDto));
		} catch (AlreadyExistsException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/{userId}/update")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdateRequest request,@PathVariable Long userId){
		try {
			User user=userService.updatUser(request, userId);
			UserDto userDto=userService.convertUserToDto(user);
			return ResponseEntity.ok(new ApiResponse("Update User Success!",userDto));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok(new ApiResponse("Delete User Success!",null));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
}
