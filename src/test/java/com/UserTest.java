package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.UserDAO;
import com.model.User;

public class UserTest {
	
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		User user= (User) context.getBean("user");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		
	
		user.setE_mail("");
		user.setMobile("");
		user.setName("Admin");
		user.setPassword("niit");
		user.setAdmin(true);
		userDAO.saveOrUpdate(user);
	}

}
