package com.sy.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sy.s1.member.MemberVO;

@Component
public class QnaInterceptor extends HandlerInterceptorAdapter{
	
	//Controller 진입 전
	//PreHandle Overriding
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		System.out.println("Qna Controller 진입 전");
		//return true 라면 Controller 전송
		//return false라면 Controller 진입 불가 - redirect로 전송

		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		// 회원만 접근 가능
		if(memberVO==null) {
				response.sendRedirect("/qna/qnaRedirect");
				return false;
			}
		return true;
	}
	
	
	

}
