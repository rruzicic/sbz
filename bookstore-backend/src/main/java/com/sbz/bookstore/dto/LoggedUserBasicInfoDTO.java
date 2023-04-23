package com.sbz.bookstore.dto;

import com.sbz.bookstore.model.Role;

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
