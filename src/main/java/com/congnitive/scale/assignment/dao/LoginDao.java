package com.congnitive.scale.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.congnitive.scale.assignment.model.BankAccount;

@Repository
public interface LoginDao extends JpaRepository<BankAccount, Integer> {

	BankAccount findByUsernameAndPassword(String username,String password);
}
