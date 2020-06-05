package com.sy.s1.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository {
	
	
	public int memberJoin(MemberVO memberVO) throws Exception;
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	public MemberVO memberCheck(MemberVO memberVO) throws Exception;

}
