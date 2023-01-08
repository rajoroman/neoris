package com.customerAccount.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10, nullable = false, unique = true)
	private String idPerson;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Gender gender;
	
	@Column(nullable = false)
	private String name;
	
	@Column(length = 3, nullable = false)
	private Integer ega;
	
	@Column(nullable = false)
	private String addres;
	
	private String numberPhone;

}
