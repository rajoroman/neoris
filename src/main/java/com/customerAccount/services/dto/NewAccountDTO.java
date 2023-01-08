package com.customerAccount.services.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewAccountDTO {

	private Long id;
	private String accountNumber;
	private Long typeAccount;	
	private Double  initialBalance;	
	private Boolean status;
	private String customerId;
}
