package com.customerAccount.services.dto;

import java.time.LocalDate;
import com.customerAccount.services.enums.TypesOfMovements;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovementBaseDTO {

//	@Temporal(TemporalType.DATE)
//	private LocalDate date;
	
	@Enumerated(EnumType.STRING) 
	private TypesOfMovements typeOfMovement;
	
	private Double value;	
}
