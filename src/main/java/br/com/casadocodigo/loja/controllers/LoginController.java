package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		
		return "loginForm";
	}
	
	@RequestMapping(value = "/login-error", method = RequestMethod.POST)
	public ModelAndView loginErrorForm() {
		ModelAndView modelAndView = new ModelAndView("loginForm"); 
		String msg = "Login ou senha inválidos!";
		modelAndView.addObject("errorMessage", msg);
		return modelAndView;
	}
	
}	
