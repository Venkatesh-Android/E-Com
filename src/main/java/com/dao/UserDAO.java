package com.dao;

import java.util.List;


import com.model.User;

public interface UserDAO {



	public List<User> list();

	
	public void saveOrUpdate(User user);


	
	public boolean isValidUser (String name, String password, boolean isAdmin);



}
