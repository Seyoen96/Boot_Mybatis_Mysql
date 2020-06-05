package com.sy.s1.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageLocaleConfig implements WebMvcConfigurer{	
	// 사용 방법
	// 1. Interceptor 만들기
	// 2. Config 파일에 생성
	//		- cookie 이용
	//		- session을 이용
	
	@Bean
	public LocaleResolver localeResolver() {
		// session을 사용하여 설정
		SessionLocaleResolver sResolver = new SessionLocaleResolver();
		// 기본으로 설정할 지역
		sResolver.setDefaultLocale(Locale.KOREAN);

		// cookie를 사용하여 설정
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		cResolver.setCookieName("lang");
		
		return cResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");		
		return localeChangeInterceptor;
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");	
	}
	
}
