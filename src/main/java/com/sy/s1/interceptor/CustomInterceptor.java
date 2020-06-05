package com.sy.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sy.s1.member.MemberVO;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter{
	
	//Controller 진입 전
	//PreHandle Overriding
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		System.out.println("Controller 진입 전");
		
		return true;
	}
	
	
	//Controller 진입 후
	//PostHandle Overriding
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Controller 진입 후");
	}	
	
	//JSP 랜더링 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("JSP 랜더링 후");
	}
	
	//비동기 요청 시 PostHandle과 afterCompletion 수행하지 않고 
	//afterConcurrentHandlingStarted 메서드를 실행
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
