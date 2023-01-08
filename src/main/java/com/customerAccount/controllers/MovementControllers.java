package com.customerAccount.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerAccount.services.AllAccountMovementsServiceRepository;
import com.customerAccount.services.dto.AccountMovementsDTO;
import com.customerAccount.services.dto.NewAccountMovementsDTO;

@RestController
@RequestMapping("movements")
public class MovementControllers {
	
	@Autowired
	private AllAccountMovementsServiceRepository accountMovementsServiceRepository;
	
	@PostMapping
	public ResponseEntity<?> saveCustomer(@RequestBody List<NewAccountMovementsDTO> newAccountMovementsDTOList) throws Exception {
		List<AccountMovementsDTO> customerSave = accountMovementsServiceRepository.save(newAccountMovementsDTOList);	
		return ResponseEntity.status(HttpStatus.CREATED).body(customerSave);
	}
	
}
