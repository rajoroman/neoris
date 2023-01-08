package com.customerAccount.services;



import com.customerAccount.services.dto.AccountTypeDTO;


public interface AllAccountTypeServiceRepository {
	
	public AccountTypeDTO withId(Long id) throws Exception;
}
