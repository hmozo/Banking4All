package com.prestamosprima.app.ws.ui.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamosprima.app.ws.security.service.AuthenticationService;
import com.prestamosprima.app.ws.security.service.AuthorizationService;
import com.prestamosprima.app.ws.service.AccountService;
import com.prestamosprima.app.ws.service.UserService;
import com.prestamosprima.app.ws.shared.dto.AccountDto;
import com.prestamosprima.app.ws.shared.dto.TransactionDto;
import com.prestamosprima.app.ws.shared.dto.UserDto;
import com.prestamosprima.app.ws.ui.model.request.TransactionDetailsRequestModel;
import com.prestamosprima.app.ws.ui.model.request.UserDetailsRequestModel;
import com.prestamosprima.app.ws.ui.model.response.AccountRest;
import com.prestamosprima.app.ws.ui.model.response.ResultRest;
import com.prestamosprima.app.ws.ui.model.response.TransactionRest;
import com.prestamosprima.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("accounts")
public class AccountController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AuthorizationService authorizationService;
	
	@Autowired
	AccountService accountService;
		
		// get User data
		@GetMapping(path = "/{accountNumber}/users/{userId}")
		public ResultRest getAccount(@PathVariable Integer accountNumber, @PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
			ResultRest resultRest = new ResultRest();
			AccountRest accountRest= new AccountRest();
			log.debug(" amount");
			//if valid token
			if(authorizationService.isUserAuthorized(request, response, userId)) {
				AccountDto accountDto= accountService.getAccount(accountNumber);
				BeanUtils.copyProperties(accountDto, accountRest);
				resultRest.setData(accountRest);
				resultRest.setStatus(Response.SC_OK);
				resultRest.setMessages("Acccount " + accountRest.getAccountNumber() + " found");
			}else {
				resultRest.setStatus(Response.SC_FORBIDDEN);
				resultRest.setMessages("User not authorized");
			}
			return resultRest;
		}
		
		//Deposit money
		@PostMapping(path = "/deposit")
		public ResultRest deposit(@Validated @RequestBody TransactionDetailsRequestModel transactionDetailsRequest, HttpServletRequest request, HttpServletResponse response) {
			ResultRest resultRest = new ResultRest();
			TransactionRest transactionRest= new TransactionRest();
			log.debug("Deposit amount");
			//if valid token, user authorized
			if(authorizationService.isUserAuthorized(request, response, transactionDetailsRequest.getUserId())) {
				TransactionDto transactionDto= accountService.deposit(transactionDetailsRequest.getAccountNumber(), transactionDetailsRequest.getAmount(), transactionDetailsRequest.getDescription());
				BeanUtils.copyProperties(transactionDto, transactionRest);
				transactionRest.setAccountNumber(transactionDto.getAccount().getAccountNumber());
				resultRest.setData(transactionRest);
				resultRest.setStatus(Response.SC_OK);
				resultRest.setMessages("Transaction " + transactionRest.getId() + " (" + transactionRest.getDescription() + ")" + " created");
			}else {
				resultRest.setStatus(Response.SC_FORBIDDEN);
				resultRest.setMessages("User not authorized");
			}
			
			return resultRest;
			
		}
		
		//Withdraw money
		@PostMapping(path = "/withdraw")
		public ResultRest withdraw(@Validated @RequestBody TransactionDetailsRequestModel transactionDetailsRequest, HttpServletRequest request, HttpServletResponse response) {
			ResultRest resultRest = new ResultRest();
			TransactionRest transactionRest= new TransactionRest();
			log.debug("Withdraw amount");
			//if valid token, user authorized
			if(authorizationService.isUserAuthorized(request, response, transactionDetailsRequest.getUserId())) {
			TransactionDto transactionDto= accountService.withdraw(transactionDetailsRequest.getAccountNumber(), transactionDetailsRequest.getAmount(), transactionDetailsRequest.getDescription());
	
			}else {
				resultRest.setStatus(Response.SC_FORBIDDEN);
				resultRest.setMessages("User not authorized");
			}
			return resultRest;
		}
		

}
