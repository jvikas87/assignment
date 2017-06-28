package com.congnitive.scale.assignment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congnitive.scale.assignment.constant.Constant;
import com.congnitive.scale.assignment.dao.BankAccountDao;
import com.congnitive.scale.assignment.dao.BeneficiaryDao;
import com.congnitive.scale.assignment.exception.BadRequestException;
import com.congnitive.scale.assignment.exception.BeneficiaryIdNotPresentException;
import com.congnitive.scale.assignment.exception.BeneficiaryNotPresentException;
import com.congnitive.scale.assignment.exception.SessionRequiredException;
import com.congnitive.scale.assignment.model.BankAccount;
import com.congnitive.scale.assignment.model.Beneficiary;
import com.congnitive.scale.assignment.response.APIResponse;

@Controller
public class BeneficiaryController {

	@Autowired
	private BeneficiaryDao beneficiaryDao;

	@Autowired
	private BankAccountDao bankAccountDao;

	@RequestMapping(value = "/add/beneficiary", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse addBeneficiary(@RequestBody Beneficiary beneficiary, HttpServletRequest request) {
		if (request.getSession() == null) {
			throw new SessionRequiredException(Constant.Message.SESSION_REQUIRED_EXCEPTION);
		}
		if (beneficiary.getBeneficiaryAccountId() == null) {
			throw new BeneficiaryIdNotPresentException(Constant.Message.BENEFIACIRY_ID_NOT_PRESENT);
		}
		BankAccount beneficiaryBankAccount = bankAccountDao.findOne(beneficiary.getBeneficiaryAccountId());
		if (beneficiaryBankAccount == null) {
			throw new BeneficiaryNotPresentException(Constant.Message.BENEFICIARY_DOES_NOT_EXIST);
		}
		BankAccount account = (BankAccount) request.getSession().getAttribute(Constant.HttpRequestAttr.USER);
		beneficiary.setAccountId(account.getId());
		beneficiaryDao.save(beneficiary);
		account.setBeneficiaryList(beneficiaryDao.findByAccountId(account.getId()));
		return new APIResponse(Constant.RESPONSE_OK, account);
	}

	@RequestMapping(value = "/remove/beneficiary", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse removeBeneficiary(@RequestBody Beneficiary beneficiary, HttpServletRequest request) {
		if (request.getSession() == null) {
			throw new SessionRequiredException(Constant.Message.SESSION_REQUIRED_EXCEPTION);
		}
		if (beneficiary.getId() == null) {
			throw new BeneficiaryIdNotPresentException(Constant.Message.BENEFICIARY_WITH_THIS_ID_NOT_PRESENT);
		}
		BankAccount beneficiaryBankAccount = bankAccountDao.findOne(beneficiary.getBeneficiaryAccountId());
		if (beneficiaryBankAccount == null) {
			throw new BeneficiaryNotPresentException(Constant.Message.BENEFICIARY_DOES_NOT_EXIST);
		}
		BankAccount account = (BankAccount) request.getSession().getAttribute(Constant.HttpRequestAttr.USER);
		beneficiary = beneficiaryDao.findOne(beneficiary.getId());
		if (!beneficiary.getAccountId().equals(account.getId())) {
			throw new BadRequestException(Constant.Message.BENEFICIARY_NOT_LINKED_WITH_USER);
		}
		beneficiaryDao.deleteById(beneficiary.getId());
		account.setBeneficiaryList(beneficiaryDao.findByAccountId(account.getId()));
		return new APIResponse(Constant.RESPONSE_OK, account);
	}
}
