package com.sy.s1.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**/")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		mv.setViewName("member/memberJoinTest");
		return mv;
	}
	
	// spring 태그 없을 때
//	@PostMapping("memberJoin")
//	public ModelAndView memberJoin(MemberVO memberVO, ModelAndView mv) throws Exception{
//		int res = memberService.memberJoin(memberVO);
////		if(res<1) {
////			mv.addObject("msg", "Join Fail");
////		} else {
////			mv.addObject("msg", "Join Success");
////		}
//		mv.setViewName("redirect:../");
//		return mv;
//	}
	
	// 비밀번호 체크 없을 때
//	@PostMapping("memberJoin")
//	public ModelAndView memberJoin(@Valid MemberVO memberVO,BindingResult bindingResult, ModelAndView mv) throws Exception{
//		if(bindingResult.hasErrors()) {
//			mv.setViewName("member/memberJoinTest");
//		} else {
//			int res = memberService.memberJoin(memberVO);
//			mv.setViewName("redirect:../");
//		}
//		
//		return mv;
//	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO,BindingResult bindingResult, ModelAndView mv) throws Exception{
		boolean result = memberService.memberError(memberVO, bindingResult);
		if(result) {
			// 에러
			mv.setViewName("member/memberJoinTest");
		} else {
			int res = memberService.memberJoin(memberVO);
			mv.setViewName("redirect:../");
		}
		
		return mv;
	}
	
	
	
	@GetMapping("memberLogin")
	public String memberLogin() throws Exception{
		return "member/memberLogin";
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO,HttpSession session,ModelAndView mv) throws Exception{
		memberVO = memberService.memberLogin(memberVO);
		if(memberVO!=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("index");
		} else {
			mv.addObject("result", "login fail");
			mv.addObject("path", "../");
			mv.setViewName("template/result");
		}
		
		return mv;
	}
	
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	

}
