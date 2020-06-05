package com.sy.s1;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// ajax 용
@RestControllerAdvice
public class ErrorRestController {

	@ExceptionHandler(NullPointerException.class)
	public String error1(){
		return "Error";
	}
	
}
