package com.customerAccount.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerAccount.models.entity.AccountType;
import com.customerAccount.models.repository.AccountTypeRepository;
import com.customerAccount.services.AllAccountTypeServiceRepository;
import com.customerAccount.services.dto.AccountTypeDTO;
import com.customerAccount.services.exceptions.ExceptionCustomService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountTypeServiceImpl implements AllAccountTypeServiceRepository{
	
	@Autowired
	private AccountTypeRepository accountTypeRepository;

	@Override
	public AccountTypeDTO withId(Long id) throws Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		Optional<AccountType> accountType = accountTypeRepository.findById(id);
		if(accountType.isPresent()) {
			return mapper.convertValue(accountType.get(), AccountTypeDTO.class);
		}
		log.info("Account Type Not Found");
		throw new ExceptionCustomService(ExceptionCustomService.ACCOUNT_TYPE_NOT_FOUND);
		
	}
}
