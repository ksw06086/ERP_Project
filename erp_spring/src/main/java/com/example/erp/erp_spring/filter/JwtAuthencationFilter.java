package com.example.erp.erp_spring.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.erp.erp_spring.security.TokenProvider;

@Component
public class JwtAuthencationFilter extends OncePerRequestFilter{

	// request가 들어왔을 때 Request Header의 Authorization 필드의 Bearer Token을 가져옴
	// 가져온 토큰을 검증하고 검증 결과를 SecurityContext에 추가
	
	@Autowired private TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = parseBeareToken(request);
			
			// token이 null이 아니고, 문자열로도 null이 아니면 
			if(token != null && !token.equalsIgnoreCase("null")) {
				// 토큰 검증해서 payload의 userEmail 가져옴
				String userEmail = tokenProvider.validate(token);
				
				// SecurityContext에 추가할 객체
				AbstractAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(userEmail,  null, AuthorityUtils.NO_AUTHORITIES);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// SecurityContextd에 AbstractAuthenticationToken 객체를 추가해서
				// 해당 Thread가 지속적으로 인증 정보를 가질 수 있도록 해줌
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); // 빈 컨텍스트
				securityContext.setAuthentication(authentication);
				SecurityContextHolder.setContext(securityContext);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		filterChain.doFilter(request, response);
	}
	
	// Request Header의 Authorization 필드의 Bearer Token을 가져오는 메서드
	public String parseBeareToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
			return bearerToken.substring(7);
		return null;
	}

}
