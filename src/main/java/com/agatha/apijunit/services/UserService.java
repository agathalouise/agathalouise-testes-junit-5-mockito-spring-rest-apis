package com.agatha.apijunit.services;

import java.util.List;

import com.agatha.apijunit.domain.User;
import com.agatha.apijunit.domain.dto.UserDTO;

public interface UserService  {

	User findById(Integer id);
	List<User> findAll();
	User create (UserDTO obj);
	User update (UserDTO obj);
	void delete(Integer id);
}
