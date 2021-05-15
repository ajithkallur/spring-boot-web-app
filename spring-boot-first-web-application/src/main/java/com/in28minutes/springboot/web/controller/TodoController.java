package com.in28minutes.springboot.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.in28minutes.springboot.web.model.Todo;
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
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id){
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public ModelAndView showAddTodoPage(ModelMap model){
		return new ModelAndView("todo");
	}
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		
		todo.setUser((String) model.get("name"));
		
		service.updateTodo(todo);

		return "redirect:/list-todos";
	}

	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public ModelAndView addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
		
		service.addTodo((String) model.get("name"), todo.getDesc(), new Date(), false);
		
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()){
			mv.setViewName("todo");
			return mv;
		}
		String name = (String) model.get("name");
		mv.setViewName("list-todos");
		mv.addObject("todos", service.retrieveTodos(name));
		return mv;
	}
	
	
}


//@Sessionattributes-> used to retrive name from session parms which are stored in login controller




