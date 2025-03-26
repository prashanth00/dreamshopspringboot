package com.wipro.dream_shops.data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.wipro.dream_shops.model.User;
import com.wipro.dream_shops.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent>{
	private final UserRepository userRepository;
	
	
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		createDefaultUserIfNotexits();
	}



	private void createDefaultUserIfNotexits() {
		// TODO Auto-generated method stub
		for(int i=1;i<=5;i++) {
			String defaultEmail="user"+i+"@email.com";
			if(userRepository.existsByEmail(defaultEmail)) {
				continue;
			}
			User user=new User();
			user.setFirstName("The User");
			user.setLastName("User"+i);
			user.setEmail(defaultEmail);
			user.setPassword("123456");
			userRepository.save(user);
			System.out.println("Default vet user "+i+" created successfully.");
		}
	}
}
