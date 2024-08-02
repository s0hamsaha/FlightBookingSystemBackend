package com.paymentgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.paymentgateway.entity.TransactionDetails;
import com.paymentgateway.service.PaymentGatewayServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentGatewayController {

	@Autowired
	private PaymentGatewayServiceImpl paymentGatewayServiceImpl;
	
	
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable Double amount)
	{
		return paymentGatewayServiceImpl.createTransaction(amount);
	}
}
