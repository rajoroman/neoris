package com.customerAccount.services;

import java.util.List;

import com.customerAccount.services.dto.AccountDTO;
import com.customerAccount.services.dto.NewAccountDTO;


public interface AllAccountServiceRepository {
	
	public List<AccountDTO> save(List<NewAccountDTO> accountDTOlist) throws Exception;

	AccountDTO withAccountNumber(String accountNumber) throws Exception;
}
