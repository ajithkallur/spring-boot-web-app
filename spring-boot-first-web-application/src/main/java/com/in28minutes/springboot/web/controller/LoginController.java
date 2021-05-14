package com.in28minutes.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.in28minutes.springboot.web.service.LoginService;



//Annotation to create a controller. This is required. jave will pick up request in here as requist mapping
@Controller 
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(ModelMap model) {	
	   // modelAndView.setViewName("login");
	    return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/ajith", method = RequestMethod.GET)
	public ModelAndView showAjithProfile(ModelMap model) {	
	    return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public ModelAndView handleLogin( @RequestParam String name, @RequestParam String password) {
		boolean isValidUser = service.validateUser(name, password);
		ModelAndView welcomemv = new ModelAndView();			
		if (isValidUser) {
			welcomemv.addObject("name", name);
			welcomemv.addObject("password", password);
			welcomemv.setViewName("welcome");
        } else {
        	welcomemv.addObject("errorMessage", "Invalid Credentials!!");
        	welcomemv.setViewName("login");
        }
		return welcomemv;		
	}
}

//Annotations Used
//@ResponseBody
//Return the string directly instead of looking for a vieworjsp
//@RequestParam- take parameter from url. send Request parms from url as parameters in function
//public String loginMessage(@RequestParam String name, ModelMap model) {
//@Sessionattributes-> used to store req parms for subsequest use. if we dont use this req parms will be gone in next page
//@Autowire->Automatic depencency injection of login service 
//@RequestMapping Annotation to map URL and type(get,post) TO METHOD
//Autowired annotion is used for dependency injection. you dont need to instatiate service again.