package com.agatha.apijunit.services.impl;

import java.util.List;
import java.util.Optional;

import com.agatha.apijunit.domain.dto.UserDTO;
import com.agatha.apijunit.services.exceptions.DataIntegratyViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agatha.apijunit.domain.User;
import com.agatha.apijunit.repositories.UserRepository;
import com.agatha.apijunit.services.UserService;
import com.agatha.apijunit.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Não encontrado"));
	}
	@Override
	public List<User> findAll(){

		return repository.findAll();
	}

	@Override
	public User create(UserDTO obj) {
		findByEmail(obj);
		return repository.save(mapper.map(obj, User.class));
	}

	@Override
	public User update(UserDTO obj) {
		findByEmail(obj);
		return repository.save(mapper.map(obj, User.class));
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	private void findByEmail(UserDTO obj){
		Optional<User> user = repository.findByEmail(obj.getEmail());
		if(user.isPresent() && !user.get().getId().equals(obj.getId())){
			throw new DataIntegratyViolationException("Email já cadastrado no sistema");
		}
	}
}
