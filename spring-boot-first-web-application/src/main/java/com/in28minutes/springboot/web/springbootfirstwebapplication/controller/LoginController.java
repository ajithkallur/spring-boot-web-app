package com.in28minutes.springboot.web.springbootfirstwebapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//   /login =>"Hello World"

//Annotation to create a controller. This is required. jave will pick up request in here as requist mapping
@Controller 
public class LoginController {
	//send Request parms from url as parameters in function
	//public String loginMessage(@RequestParam String name, ModelMap model) {
	
	// RequestMapping Annotation to map URL TO METHOD
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		//return new ModelAndView("login", model);
		return "login";	
	}
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name) {
		model.put("name", name);
		return "welcome";		
	}
}

//@ResponseBody
//Return the string directly instead of looking for a vieworjsp
//@RequestParam- take parameter from url