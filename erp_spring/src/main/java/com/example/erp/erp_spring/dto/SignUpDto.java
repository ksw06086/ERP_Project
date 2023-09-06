package com.example.erp.erp_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// <DTO>
// - view에서 데이터를 Controller로 가져올 때 따로따로가 아니라 
//   객체 하나로 한번에 가져올 수 있음 
// - DTO 객체로 만들어 view에서 보내주는 데이터를 한번에 객체에 넣어 가져와 사용함

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
	private String userEmail;
	private String userPassword;
	private String userPasswordCheck;
	private String userNickname;
	private String userPhoneNumber;
	private String userAddress;
	private String userAddressDetail;
}
