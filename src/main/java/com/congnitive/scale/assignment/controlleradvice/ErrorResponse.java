package com.congnitive.scale.assignment.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.congnitive.scale.assignment.constant.Constant;
import com.congnitive.scale.assignment.exception.BeneficiaryIdNotPresentException;
import com.congnitive.scale.assignment.exception.InSufficientAccountBalanceException;
import com.congnitive.scale.assignment.exception.InvalidBeneficiaryException;
import com.congnitive.scale.assignment.exception.SessionRequiredException;
import com.congnitive.scale.assignment.exception.SessionTimeOutException;
import com.congnitive.scale.assignment.response.APIResponse;

@ControllerAdvice
public class ErrorResponse {

	
	@ExceptionHandler(value = SessionTimeOutException.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public APIResponse invalidAccountMoney(SessionTimeOutException ex) {
		return new APIResponse(Constant.RESPONSE_ERROR, ex.getMessage());
	}
	
	@ExceptionHandler(value = SessionRequiredException.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public APIResponse sessionRequiredException(SessionRequiredException ex) {
		return new APIResponse(Constant.RESPONSE_ERROR, ex.getMessage());
	}
	@ExceptionHandler(value = InvalidBeneficiaryException.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public APIResponse invalidBeneficiaryException(InvalidBeneficiaryException ex) {
		return new APIResponse(Constant.RESPONSE_ERROR, ex.getMessage());
	}
	@ExceptionHandler(value = InSufficientAccountBalanceException.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public APIResponse inSufficientAccountBalanceException(SessionTimeOutException ex) {
		return new APIResponse(Constant.RESPONSE_ERROR, ex.getMessage());
	}
	
	
	@ExceptionHandler(value = BeneficiaryIdNotPresentException.class)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public APIResponse beneficiaryIdNotPresentException(SessionTimeOutException ex) {
		return new APIResponse(Constant.RESPONSE_ERROR, ex.getMessage());
	}
}
