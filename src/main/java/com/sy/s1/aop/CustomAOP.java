package com.sy.s1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAOP {
	
	@Before("execution(* com.sy.s1.board.notice.*Service.*(..))")
	public void before() throws Exception{
		System.out.println("Before Method");
	}
	
	@AfterReturning("execution(* com.sy.s1.board.notice.*Service.get*(..))")
	public void afterReturning() throws Exception{
		System.out.println("After Returning Method");
	}
	
	@AfterThrowing("execution(* com.sy.s1.board.notice.*Service.get*(..))")
	public void afterThrowing() throws Exception{
		System.out.println("After Throwing Method");
	}
	
	@After("execution(* com.sy.s1.board.notice.*Service.get*(..))")
	public void after() throws Exception{
		System.out.println("After Method");
	}
	
	@Around("execution(* com.sy.s1.board.notice.*Service.get*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("Before");
		// 해당 메서드를 진행
		Object obj = joinPoint.proceed();
		System.out.println("After");
		return obj;
	}
	
}
