package com.customerAccount.services.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestReportDTO {
	private String customerId;
	private LocalDate from;
	private LocalDate to;
}
