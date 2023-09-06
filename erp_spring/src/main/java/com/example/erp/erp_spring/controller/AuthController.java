package com.example.erp.erp_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.erp.erp_spring.dto.ResponseDto;
import com.example.erp.erp_spring.dto.SignInDto;
import com.example.erp.erp_spring.dto.SignUpDto;
import com.example.erp.erp_spring.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired AuthService authService;
	
	@PostMapping("signup")
	public ResponseDto<?> signUp( @RequestBody SignUpDto requestBody ) {
		ResponseDto<?> result = authService.signUp(requestBody);
		return result;
	}
	
	@PostMapping("signin")
	public ResponseDto<?> signin( @RequestBody SignInDto requestBody ) {
		ResponseDto<?> result = authService.signIn(requestBody);
		return result;
	}
	
}
