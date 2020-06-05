package com.sy.s1;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// 예외 전문 controller
@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView error1() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView error2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/serverError");
		return mv;
	}	
}
