package br.com.casadocodigo.loja.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView trataExeptionGenerica(Exception exception) {
		exception.printStackTrace();
		String msg = "Desculpe, ocorreu um erro no processamento. Tente novamente.";
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}
	
}
