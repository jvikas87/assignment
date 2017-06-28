package com.congnitive.scale.assignment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.congnitive.scale.assignment.model.Beneficiary;

@Repository
public interface BeneficiaryDao extends JpaRepository<Beneficiary, Integer> {

	List<Beneficiary> findByAccountId(Integer accountId);
	
	@Transactional
	void deleteById(Integer id);
}
