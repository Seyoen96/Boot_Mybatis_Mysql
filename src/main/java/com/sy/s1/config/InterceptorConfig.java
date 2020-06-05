package com.sy.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sy.s1.interceptor.CustomInterceptor;
import com.sy.s1.interceptor.NoticeInterceptor;
import com.sy.s1.interceptor.QnaInterceptor;
import com.sy.s1.interceptor.QnaWriterInterceptor;

//xml에 <Interceptor> 대신 하는 역할
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	//springBoot 2.0 이후부터 Deprecated
	//WebMvcConfigurerAdapter	
	
	//springBoot 2.0 이후부터 다음을 사용
	//WebMvcConfigurer
	
	
	@Autowired
	private NoticeInterceptor noticeInterceptor;
	@Autowired
	private QnaInterceptor qnaInterceptor;
	@Autowired
	private QnaWriterInterceptor qnaWriterInterceptor;


	//Interceptor를 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 순서대로 작동
		
		// Method Chaining
		// 적용할 Interceptor를 등록
//		registry.addInterceptor(noticeInterceptor)
//		// Interceptor 사용할 url 등록
//		.addPathPatterns("/notice/noticeWrite")
//		.addPathPatterns("/notice/noticeUpdate")
//		.addPathPatterns("/notice/noticeDelete")
//		// Interceptor에서 제외할 url 등록
//		.excludePathPatterns("/notice/noticeList")
//		.excludePathPatterns("/notice/noticeSelect");	
		
		registry.addInterceptor(qnaInterceptor)
		.addPathPatterns("/qna/*")
		.excludePathPatterns("/qna/qnaDelete")
		.excludePathPatterns("/qna/qnaUpdate")
		.excludePathPatterns("/qna/qnaList")
		.excludePathPatterns("/qna/qnaRedirect");
		
		registry.addInterceptor(qnaWriterInterceptor)
		.addPathPatterns("/qna/qnaDelete")
		.addPathPatterns("/qna/qnaUpdate");

		//WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
