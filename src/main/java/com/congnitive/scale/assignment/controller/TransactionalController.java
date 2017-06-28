package com.congnitive.scale.assignment.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congnitive.scale.assignment.constant.Constant;
import com.congnitive.scale.assignment.dao.BeneficiaryDao;
import com.congnitive.scale.assignment.exception.InvalidBeneficiaryException;
import com.congnitive.scale.assignment.exception.SessionRequiredException;
import com.congnitive.scale.assignment.model.BankAccount;
import com.congnitive.scale.assignment.model.Beneficiary;
import com.congnitive.scale.assignment.model.Transaction;
import com.congnitive.scale.assignment.pojo.TransactionDetail;
import com.congnitive.scale.assignment.response.APIResponse;
import com.congnitive.scale.assignment.service.TransactionService;

@Controller
public class TransactionalController {

	@Autowired
	private BeneficiaryDao beneficiaryDao;

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse transact(@RequestBody TransactionDetail transactionDetail, HttpServletRequest request) {
		if (request.getSession() == null) {
			throw new SessionRequiredException(Constant.Message.SESSION_REQUIRED_EXCEPTION);
		}
		BankAccount account = (BankAccount) request.getSession().getAttribute(Constant.HttpRequestAttr.USER);
		HttpSession session = request.getSession();
		long now = Calendar.getInstance().getTime().getTime();
		long loginTime = (Long) session.getAttribute(Constant.HttpRequestAttr.CURRENT_TIME_STAMP);
		long elapsedTime = now - loginTime;
		if (elapsedTime > Constant.MILLIS_IN_TEN_MINUTE) {
			session.invalidate();
			throw new SessionException(Constant.Message.SESSION_TIME_OUT);
		}
		List<Beneficiary> list = beneficiaryDao.findByAccountId(account.getId());
		if (list == null || list.isEmpty()) {
			throw new InvalidBeneficiaryException(Constant.Message.BENEFICIARY_DOES_NOT_EXIST);
		}
		boolean isValid = false;
		for (Beneficiary beneficiary : list) {
			if (beneficiary.getBeneficiaryAccountId() == transactionDetail.getBeneficiaryId()) {
				isValid = true;
			}
		}
		if (!isValid) {
			throw new InvalidBeneficiaryException(Constant.Message.BENEFICIARY_DOES_NOT_EXIST);
		}
		Transaction transaction = transactionService.transact(transactionDetail, account);
		return new APIResponse(Constant.RESPONSE_OK, transaction);
	}

	@RequestMapping(value = "/transaction/detail", method = RequestMethod.GET)
	@ResponseBody
	public APIResponse transactionDetail(
			@DateTimeFormat(pattern = Constant.DATE_TIME_FORMAT) @RequestParam("startDate") Date startDate,
			@DateTimeFormat(pattern = Constant.DATE_TIME_FORMAT) @RequestParam("endDate") Date endDate,
			HttpServletRequest request) {
		if (request.getSession() == null) {
			throw new SessionRequiredException(Constant.Message.SESSION_REQUIRED_EXCEPTION);
		}
		BankAccount account = (BankAccount) request.getSession().getAttribute(Constant.HttpRequestAttr.USER);
		List<Transaction> transactionList = transactionService.getTransactionByFromAccountId(account.getId(), startDate,
				endDate);
		return new APIResponse(Constant.RESPONSE_OK, transactionList);
	}

}
