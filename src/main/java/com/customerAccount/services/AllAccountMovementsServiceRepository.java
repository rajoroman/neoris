package com.customerAccount.services;

import java.util.List;

import com.customerAccount.services.dto.AccountMovementsDTO;
import com.customerAccount.services.dto.NewAccountMovementsDTO;

public interface AllAccountMovementsServiceRepository {
	
	List<AccountMovementsDTO> save(List<NewAccountMovementsDTO> accountMovementsDTOList) throws Exception;
}
