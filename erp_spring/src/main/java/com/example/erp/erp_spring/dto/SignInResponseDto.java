package com.example.erp.erp_spring.dto;

import com.example.erp.erp_spring.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//<DTO>
//- ResponseDto에서 제네릭 D 타입에 해당하는 클래스
//- { token, exprTime }의 값을 넣을 수 있는 클래스타입

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
	private String token;
	private int exprTime;
	private UserEntity user;
}
