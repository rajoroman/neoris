package com.customerAccount.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerAccount.models.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long> {

}
