package com.sbz.bank.dto;

import com.sbz.bank.model.Role;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoggedUserBasicInfoDTO {
	String name;
	Role role;
}
