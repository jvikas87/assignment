package com.congnitive.scale.assignment.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congnitive.scale.assignment.constant.Constant;
import com.congnitive.scale.assignment.dao.BankAccountDao;
import com.congnitive.scale.assignment.model.BankAccount;
import com.congnitive.scale.assignment.response.APIResponse;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountDao bankAccountDao;

	@RequestMapping(value = "/bankaccount/create", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse createAccount(@RequestBody BankAccount bankAccount) {
		bankAccountDao.save(bankAccount);
		return new APIResponse(Constant.RESPONSE_OK, bankAccount);
	}

	@RequestMapping(value = "/bankaccount/balance", method = RequestMethod.GET)
	@ResponseBody
	public APIResponse accountBalance(@RequestParam("accountNumber") String accountNumber, HttpServletRequest request) {
		BankAccount bankAccount = bankAccountDao.findByAccountNumber(accountNumber);
		HttpSession session = request.getSession();
		long now = Calendar.getInstance().getTime().getTime();
		long loginTime = (Long) session.getAttribute(Constant.HttpRequestAttr.CURRENT_TIME_STAMP);
		long elapsedTime = now - loginTime;
		if(elapsedTime > Constant.MILLIS_IN_TEN_MINUTE){
			session.invalidate();
			throw new SessionException(Constant.Message.SESSION_TIME_OUT);
		}
		return new APIResponse(Constant.RESPONSE_OK, bankAccount);
	}
}
