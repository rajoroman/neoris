package com.customerAccount.services.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {
	private Long id;
	private String idPerson;
	private String name;
	private GenderDTO gender;
	private Integer ega;
	private String addres;
	private String numberPhone;
}
