package com.congnitive.scale.assignment.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congnitive.scale.assignment.constant.Constant;
import com.congnitive.scale.assignment.dao.LoginDao;
import com.congnitive.scale.assignment.model.BankAccount;
import com.congnitive.scale.assignment.pojo.UsernamePasswordPayload;
import com.congnitive.scale.assignment.response.APIResponse;

@Controller
public class LoginController {

	@Autowired
	private LoginDao loginDao;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse login(@RequestBody UsernamePasswordPayload usernamePassword, HttpServletRequest request) {
		BankAccount account = loginDao.findByUsernameAndPassword(usernamePassword.getUsername(),
				usernamePassword.getPassword());
		request.getSession().setAttribute(Constant.HttpRequestAttr.USER, account);
		request.getSession().setAttribute(Constant.HttpRequestAttr.CURRENT_TIME_STAMP,
				Calendar.getInstance().getTime().getTime());
		return new APIResponse(Constant.RESPONSE_OK, account);
	}
}
