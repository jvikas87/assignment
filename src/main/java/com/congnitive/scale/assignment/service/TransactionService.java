package com.congnitive.scale.assignment.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congnitive.scale.assignment.constant.Constant;
import com.congnitive.scale.assignment.dao.BankAccountDao;
import com.congnitive.scale.assignment.dao.TransactionDao;
import com.congnitive.scale.assignment.exception.InSufficientAccountBalanceException;
import com.congnitive.scale.assignment.fixer.io.api.ConversionUtil;
import com.congnitive.scale.assignment.model.BankAccount;
import com.congnitive.scale.assignment.model.Transaction;
import com.congnitive.scale.assignment.model.Transaction.TransactionType;
import com.congnitive.scale.assignment.pojo.ConversionInfo;
import com.congnitive.scale.assignment.pojo.TransactionDetail;

@Service
public class TransactionService {
	@Autowired
	private BankAccountDao bankAccountDao;

	@Autowired
	private TransactionDao transactionDao;

	@Transactional
	public Transaction transact(TransactionDetail transactionDetail, BankAccount account) {
		BankAccount beneficiaryBankAccount = bankAccountDao.findOne(transactionDetail.getBeneficiaryId());

		double amount = isPossible(account, beneficiaryBankAccount, transactionDetail);

		Transaction debitTransaction = new Transaction();
		debitTransaction.setFromAccount(account.getId());
		debitTransaction.setToAccount(beneficiaryBankAccount.getId());
		debitTransaction.setTransactionType(TransactionType.DEBIT);
		debitTransaction.setTransferAmount(amount);

		Transaction creditTransaction = new Transaction();
		creditTransaction.setToAccount(account.getId());
		creditTransaction.setFromAccount(beneficiaryBankAccount.getId());
		creditTransaction.setTransactionType(TransactionType.CREDIT);
		creditTransaction.setTransferAmount(transactionDetail.getAmount());

		account.setAccountBalance(account.getAccountBalance() - amount);
		beneficiaryBankAccount
				.setAccountBalance(beneficiaryBankAccount.getAccountBalance() + transactionDetail.getAmount());

		transactionDao.save(debitTransaction);
		transactionDao.save(creditTransaction);

		bankAccountDao.save(account);
		bankAccountDao.save(beneficiaryBankAccount);

		return debitTransaction;
	}

	private double isPossible(BankAccount account, BankAccount beneficiaryBankAccount,
			TransactionDetail transactionDetail) {
		Double moneyInOtherCurrency = transactionDetail.getAmount();
		if (account.getCurrency() != beneficiaryBankAccount.getCurrency()) {
			ConversionInfo conversionInfo = ConversionUtil
					.getConversionInfo(beneficiaryBankAccount.getCurrency().name());
			Double factor = conversionInfo.getRates().get(account.getCurrency().name());
			moneyInOtherCurrency = factor * transactionDetail.getAmount();
		}
		if (account.getAccountBalance() < transactionDetail.getAmount()) {
			throw new InSufficientAccountBalanceException(Constant.Message.INSUFFICIENT_ACCOUNT_BALANCE_EXCEPTION);
		}
		return moneyInOtherCurrency;
	}

	public List<Transaction> getTransactionByFromAccountId(Integer accountId, Date startDate, Date endDate) {
		return transactionDao.findByFromAccountIdAndDatesBetween(accountId,startDate,endDate);
	}
}
