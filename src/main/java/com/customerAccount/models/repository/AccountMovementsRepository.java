package com.customerAccount.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerAccount.models.entity.Account;
import com.customerAccount.models.entity.AccountMovements;

public interface AccountMovementsRepository extends JpaRepository<AccountMovements, Long> {
	List<AccountMovements> findByAccount(Account account);
}
