package com.example.erp.erp_spring.service;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.erp.erp_spring.dto.ResponseDto;
import com.example.erp.erp_spring.dto.SignInDto;
import com.example.erp.erp_spring.dto.SignInResponseDto;
import com.example.erp.erp_spring.dto.SignUpDto;
import com.example.erp.erp_spring.entity.UserEntity;
import com.example.erp.erp_spring.repository.UserRepository;
import com.example.erp.erp_spring.security.TokenProvider;

@Service
public class AuthService {
	@Autowired UserRepository userRepository;
	@Autowired TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	// 회원가입
	public ResponseDto<?> signUp(SignUpDto dto) {
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		String userPasswordCheck = dto.getUserPasswordCheck();
		
		// email 중복 확인
		try {
			if(userRepository.existsById(userEmail))
				return ResponseDto.setFailed("Existed Email!");
		} catch (Exception e) {
			return ResponseDto.setFailed("Database Error!");
		}
		
		// 비밀번호가 서로 다르면 Failed response 반환
		try {
			if(!userPassword.equals(userPasswordCheck))
				return ResponseDto.setFailed("Password does not matched!");
		} catch (Exception e) {
			return ResponseDto.setFailed("Database Error!");
		}
		
		// UserEntity 생성
		UserEntity userEntity = new UserEntity(dto);
		
		// 비밀번호 암호화
		String encodePassword = passwordEncoder.encode(userPassword);
		userEntity.setUserPassword(encodePassword);
		
		// UserRepository를 이용해서 데이터베이스에 Entity 저장
		try {
			userRepository.save(userEntity);
		} catch (Exception e) {
			return ResponseDto.setFailed("Database Error!");
		}
		
		// 성공시 success response 반환!
		return ResponseDto.setSuccess("Sign Up success!", null);
	}
	
	// 로그인
	public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.findByUserEmail(userEmail);
			// 잘못된 이메일
			if(userEmail == null) return ResponseDto.setFailed("Email Sign In Failed!");
			// 잘못된 패스워드
			if(!passwordEncoder.matches(userPassword, userEntity.getUserPassword()))
				return ResponseDto.setFailed("Password Sign In Failed!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.setFailed("Database Error!");
		}
		
		userEntity.setUserPassword("");
		
		String token = tokenProvider.create(userEmail);
		int exprTime = 3600000;
		
		SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
		return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
	}
}
