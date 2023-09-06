package com.example.erp.erp_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//<DTO>
//- Controller에서 데이터를 View로 보낼 때 따로따로가 아니라 
//	객체 하나로 한번에 보낼 수 있음 
//- DTO 객체로 만들어 한번에 객체에 넣어 보내줌

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
	private boolean result;
	private String message;
	private D data;
	
	public static <D> ResponseDto<D> setSuccess(String message, D data){
		return ResponseDto.set(true, message, data);
	}
	
	public static <D> ResponseDto<D> setFailed(String message) {
		return ResponseDto.set(false, message, null);
	}
}
