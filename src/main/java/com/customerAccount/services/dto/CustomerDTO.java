package com.customerAccount.services.dto;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO extends PersonDTO implements Serializable  {
	
	private String customerId;
	private String password;
	private Boolean status;
	private List<AccountDTO> accounts;
}
