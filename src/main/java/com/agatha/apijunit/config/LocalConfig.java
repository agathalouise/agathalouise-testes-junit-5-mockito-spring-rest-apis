package com.agatha.apijunit.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.agatha.apijunit.domain.User;
import com.agatha.apijunit.repositories.UserRepository;


@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Agatha Louise", "agatha@email.com", "123");
		User u2 = new User(null, "Maria Suelen", "Suelen@email.com", "123");
		
		repository.saveAll(List.of(u1, u2));
	}
}
