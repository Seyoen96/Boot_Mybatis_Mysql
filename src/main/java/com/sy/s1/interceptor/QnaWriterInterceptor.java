package com.sy.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sy.s1.board.BoardVO;
import com.sy.s1.board.qna.QnaRepository;
import com.sy.s1.member.MemberVO;

@Component
public class QnaWriterInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	QnaRepository qnaRepository;
	
	//Controller 진입 전
	//PreHandle Overriding
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String method = request.getMethod();
		// GET 방식으로 들어왔을 때 검증을 진행
		if(method.equals("get")) {
			// parameter로 num을 받아옴
			int num = Integer.parseInt(request.getParameter("num"));	
			BoardVO boardVO = new BoardVO();
			boardVO.setNum(num);
			boardVO = qnaRepository.getSelectOne(boardVO);
			// select 페이지의  글번호의 작성자 id
			String writerID = boardVO.getWriter();			
			// session의 로그인 된 멤버 정보
			MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
			
			// 회원만 접근 가능
			if(memberVO!=null) {
				// 작성자가 아니라면
				if(!memberVO.getId().equals(writerID)) {
					response.sendRedirect("/qna/qnaRedirect");
					return false;
				}
			}
		} else {
			return true;
		}
		
		return true;
	}
	

}
