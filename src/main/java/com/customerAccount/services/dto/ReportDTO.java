package com.customerAccount.services.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportDTO  {
	
	private LocalDate date;
	private String nameCustomer;
	private String accountNumber;
	private String typeAccount;
	private Double initialBalance;
	private Boolean status;
	private Double value;
	private Double balance;
}
