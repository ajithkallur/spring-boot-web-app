package com.in28minutes.springboot.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.in28minutes.springboot.web.service.LoginService;
import com.in28minutes.springboot.web.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public ModelAndView showTodos(ModelMap model){
		ModelAndView mv = new ModelAndView("list-todos");
		String name = (String) model.get("name");
		mv.addObject("todos", service.retrieveTodos(name));
		return mv;
	}
	
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public ModelAndView showAddTodoPage(ModelMap model){
		return new ModelAndView("todo");
	}

	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @RequestParam String desc){
		service.addTodo((String) model.get("name"), desc, new Date(), false);
		return "redirect:/list-todos";
	}
}


//@Sessionattributes-> used to retrive name from session parms which are stored in login controller




