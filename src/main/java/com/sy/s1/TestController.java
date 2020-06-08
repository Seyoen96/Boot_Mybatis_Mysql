package com.sy.s1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sy.s1.util.Pager;

@Controller
public class TestController {	
	// REST 방식으로 받아오기
	// path variable이 우선 (parameter X)
	
	// 변수는 어디에 써도 상관 없음
	// but, primitive타입의 변수일 경우  @PathVariable value 지정 필요
	@GetMapping("/test/select/{num}")
	public void test(@PathVariable(value = "num") String num) {
		System.out.println("num: "+num);
	}
	
	@GetMapping("/test/select/{num}/{name}")
	public void test3(@PathVariable String num, @PathVariable String name) {
		System.out.println("num: "+num);
		System.out.println("name: "+name);
	}
	
	// 변수명 같으면 annotation안에 value 지정하지 않아도 됨
	// 객체의 멤버 변수명 같으면 객체도 매핑 가능 
	@GetMapping("/test/list/{curPage}/{kind}/{search}")
//	public void test2(@PathVariable int curPage,@PathVariable String kind, @PathVariable String search) {
	public void test2(Pager pager) {		
		System.out.println("curPage: "+pager.getCurPage());
		System.out.println("kind:"+pager.getKind());
	}
	
}
