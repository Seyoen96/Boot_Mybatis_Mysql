package com.sy.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;	
	
	public int memberJoin(MemberVO memberVO) throws Exception{
		return memberRepository.memberJoin(memberVO);
	}
	
	//검증 메서드 추가
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result = false;			// 에러 없음
		//1. 기본 annotation 제공 검증
//		result = bindingResult.hasErrors();
		if(bindingResult.hasErrors()) {
			result = true;
		}
		
		//2. pw 체크 일치 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			bindingResult.rejectValue("pwCheck", "memberVO.password.notEqual");
			result= true;
		} 
		
		//3. ID 중복 검증
		//DB에서 ID 조회
		if(memberRepository.memberCheck(memberVO)!=null) {
			bindingResult.rejectValue("id","memberVO.id.equal");
			result = true;
		}
		return result;
	}
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return memberRepository.memberLogin(memberVO);
	}

}
