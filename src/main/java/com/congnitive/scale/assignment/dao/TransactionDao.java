package com.congnitive.scale.assignment.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.congnitive.scale.assignment.model.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

	@Query("select t from Transaction t where t.fromAccount=:accountId and t.transactionDate>=:startDate and t.transactionDate<=:endDate")
	List<Transaction> findByFromAccountIdAndDatesBetween(@Param("accountId")Integer accountId, 
			@Param("startDate")Date startDate,@Param("endDate") Date endDate);
}
