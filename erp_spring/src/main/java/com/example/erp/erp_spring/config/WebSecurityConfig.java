package com.example.erp.erp_spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.erp.erp_spring.filter.JwtAuthencationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired private JwtAuthencationFilter jwtAuthencationFilter;
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// cors 정책 (현재는 Application에서 작업을 해줬으므로 기본 설정 사용) 
			.cors().and()
			// csrf 대책 (현재는 CSRF에 대한 대책을 비활성화) - 사이트에 몇 천 유저일 때의 대책
			.csrf().disable()
			// Basic 인증 (현재는 Bearer token 인증방법을 사용하기 때문에 비활성화)
			.httpBasic().disable()
			// 세션 사용 관련 (현재는 Session 기반 인증을 사용하지 않기 때문에 상태를 없앰
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			// '/', '/api/auth' 모듈에 대해서는 모두 허용 (인증을 하지 않고 사용 가능하게 함)
			.authorizeRequests().antMatchers("/", "/api/auth/**").permitAll()
			// 나머지 Request에 대해서는 모두 인증된 사용자만 사용가능하게 함
			.anyRequest().authenticated();
			
		httpSecurity.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
	
}
