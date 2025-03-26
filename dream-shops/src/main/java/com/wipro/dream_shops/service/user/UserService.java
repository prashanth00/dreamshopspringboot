package com.wipro.dream_shops.service.user;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.wipro.dream_shops.dto.UserDto;
import com.wipro.dream_shops.exceptions.AlreadyExistsException;
import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.User;
import com.wipro.dream_shops.repository.UserRepository;
import com.wipro.dream_shops.requests.CreateUserRequest;
import com.wipro.dream_shops.requests.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found!"));
	}

	@Override
	public User createUser(CreateUserRequest request) {
		// TODO Auto-generated method stub
		return Optional.of(request).filter(user->!userRepository.existsByEmail(request.getEmail())).map(req->{
			User user=new User();
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setFirstName(request.getFirstName());
			user.setLastName(request.getLastName());
			return userRepository.save(user);
		}).orElseThrow(()->new AlreadyExistsException("Oops! "+request.getEmail()+"already exists!"));
	}

	@Override
	public User updatUser(UserUpdateRequest request, Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).map(existingUser->{
			existingUser.setFirstName(request.getFirstName());
			existingUser.setLastName(request.getLastName());
			return userRepository.save(existingUser);
		}).orElseThrow(()->new ResourceNotFoundException("User Not Found")); 
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userRepository.findById(userId).ifPresentOrElse(userRepository::delete, ()->{
			throw new ResourceNotFoundException("User not found!");
		});
	}
	
	@Override
	public UserDto convertUserToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
}
