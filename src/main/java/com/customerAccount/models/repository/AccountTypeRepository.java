package com.customerAccount.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerAccount.models.entity.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
