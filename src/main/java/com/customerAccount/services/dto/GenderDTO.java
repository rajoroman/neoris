package com.customerAccount.services.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class GenderDTO implements Serializable {
	
	private Long   id; 	
	private String Description;
}
