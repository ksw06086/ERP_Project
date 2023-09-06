package com.example.erp.erp_spring.dto;

import com.example.erp.erp_spring.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserResponseDto {
	private UserEntity user;
}
