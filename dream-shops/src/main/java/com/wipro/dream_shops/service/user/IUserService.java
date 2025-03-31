package com.wipro.dream_shops.service.user;

import com.wipro.dream_shops.dto.UserDto;
import com.wipro.dream_shops.model.User;
import com.wipro.dream_shops.requests.CreateUserRequest;
import com.wipro.dream_shops.requests.UserUpdateRequest;

public interface IUserService {
	User getUserById(Long userId);
	User createUser(CreateUserRequest request);
	User updatUser(UserUpdateRequest request,Long userId);
	void deleteUser(Long userId);
	UserDto convertUserToDto(User user);
	User getAuthenticatedUser();
}
