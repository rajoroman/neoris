package com.customerAccount.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customerAccount.models.entity.AccountMovements;
import com.customerAccount.models.repository.AccountMovementsRepository;
import com.customerAccount.services.AllAccountMovementsServiceRepository;
import com.customerAccount.services.AllAccountServiceRepository;
import com.customerAccount.services.dto.AccountDTO;
import com.customerAccount.services.dto.AccountMovementsDTO;
import com.customerAccount.services.dto.NewAccountMovementsDTO;
import com.customerAccount.services.enums.TypesOfMovements;
import com.customerAccount.services.exceptions.ExceptionCustomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountMovementsServiceImpl implements AllAccountMovementsServiceRepository{
	
	private static final String ERROR_TRANSACTION = "Error transaction";
	
	@Autowired
	private AccountMovementsRepository accountMovementsRepository;
	
	@Autowired
	private AllAccountServiceRepository accountServiceRepository;

	@Transactional
	@Override
	public List<AccountMovementsDTO> save(List<NewAccountMovementsDTO> accountMovementsDTOList) throws Exception{
		List<AccountMovementsDTO> accountMovementsListDTO = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			accountMovementsDTOList.forEach(newMovenment -> {
					try {
						AccountDTO accountDTO = accountServiceRepository.withAccountNumber(newMovenment.getAccountNumber());
						Double balanceCurrent = accountDTO.getInitialBalance();
						if(accountDTO.getMovements() != null && accountDTO.getMovements().size() > 0 ) {
							Optional<AccountMovementsDTO> accountMovementsOpt = accountDTO.getMovements()
							.stream()
							.sorted((emit1, emit2) -> ((Long) emit2.getId()).compareTo(emit1.getId()))
							.findFirst();
							balanceCurrent = accountMovementsOpt.get().getBalance();
						}
						AccountMovementsDTO accountMovementsDTO = mapper.convertValue(newMovenment, AccountMovementsDTO.class);
						accountMovementsDTO.setAccount(accountDTO);
						Double newBalance = 0d;
						if(accountMovementsDTO.getTypeOfMovement().equals(TypesOfMovements.CREDITO)) {
							newBalance = balanceCurrent + accountMovementsDTO.getValue();
						}else if(accountMovementsDTO.getTypeOfMovement().equals(TypesOfMovements.DEBITO)){
							if(accountMovementsDTO.getValue() <= balanceCurrent) {
								newBalance = balanceCurrent - accountMovementsDTO.getValue();
							}else {
								throw new ExceptionCustomService(ExceptionCustomService.INSUFFICIENT_BALANCE);
							}
						}
						accountMovementsDTO.setBalance(newBalance);
						accountMovementsDTO.setAccount(accountDTO);
						accountMovementsListDTO.add(accountMovementsDTO);
					} catch (Exception e) {
						log.info(ERROR_TRANSACTION,e);
						throw new RuntimeException(e);
					} 
			});
			List<AccountMovements> accountMovementsList = this.toEntity(accountMovementsListDTO);
			accountMovementsList = accountMovementsRepository.saveAll(accountMovementsList);
			return this.toDTO(accountMovementsList);
		} catch (DataIntegrityViolationException | JpaSystemException e) {
			log.info(ERROR_TRANSACTION, e);
			throw new RuntimeException(e);
		}
	}
	
	private List<AccountMovements> toEntity(List<AccountMovementsDTO> accountMovementsDTOList) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		List<AccountMovements> result = new ArrayList<>();
		accountMovementsDTOList.forEach(movementDTO -> {
			AccountMovements accountMovement = mapper.convertValue(movementDTO, AccountMovements.class);
			result.add(accountMovement);
		});
		return result;
	}
	
	private List<AccountMovementsDTO> toDTO(List<AccountMovements> accountMovementsList) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		List<AccountMovementsDTO> result = new ArrayList<>();
		accountMovementsList.forEach(movement -> {
			AccountMovementsDTO accountMovementsDTO = mapper.convertValue(movement, AccountMovementsDTO.class);
			result.add(accountMovementsDTO);
		});
		return result;
	}
}
