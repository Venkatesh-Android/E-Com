package com.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.UserDAO;
import com.model.User;

@Controller
public class HomeController {
	
	  @Autowired
	    private UserDAO userDAO;
	
	@RequestMapping("/")
	public String land()
	
	{
		System.out.println("Entered Landing controller");
		return("homeside1");
		
	}
	@RequestMapping("/homeside1")
	public String home()
	
	{
		System.out.println("Back to Home controller");
		return("homeside1");
		
	}
	@RequestMapping("/LoginTry2")
	public String log()
	
	{
		System.out.println("Entered Login Page");
		return("LoginTry2");
		
	}
	@RequestMapping("/Apple_Prds-Page")
	public String apple()
	
	{
		System.out.println("Entered Apple Page");
		return("Apple_Prds-Page");
		
	}
	@RequestMapping("/Android_Prds-Page")
	public String android()
	
	{
		System.out.println("Entered Android Page");
		return("Android_Prds-Page");
		
	}
	@RequestMapping("/Windows_Prds-Page")
	public String windows()
	
	{
		System.out.println("Entered Windows Page");
		return("Windows_Prds-Page");
		
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUserPost(@ModelAttribute("ObjRs") User user, BindingResult result, Model model) {
    	

        if(result.hasErrors()){
            return "/register";
        }

      List<User> userList = userDAO.list();
        for (int i=0; i< userList.size(); i++)
        {
		        if(user.getName().equals(userList.get(i).getName())){
		            model.addAttribute("usernameMsg", "Username already exists");
		
		            return "registerCustomer";
		        }
        }
		return("LoginTry2") ; // better go to new page and throw register successfully along with a link to sign in...
    }
	
	@RequestMapping("/isValidUser")
		public ModelAndView showMessage(@RequestParam(value = "username") String username,
				@RequestParam(value = "password") String password) {
			System.out.println("in user controller");

			String message;
			ModelAndView mv;
			if (userDAO.isValidUser(username, password, true)) {
				message = "Valid credentials";
				mv = new ModelAndView("admin"); //take to admin page 
			} 
				else if(userDAO.isValidUser(username, password, false)){
				message="welcome user";
				mv=new ModelAndView("homeside1"); //take to end user page that is Landing page
			
			}
				else {
					message = "Invalid credentials";
					mv = new ModelAndView("LoginTry2"); //try login again
				}
			mv.addObject("message", message);
			mv.addObject("name", username);


		return mv;
	}

}
