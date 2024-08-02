package com.paymentgateway.service;

import com.paymentgateway.entity.TransactionDetails;
import com.razorpay.Order;

public interface PaymentGatewayService {

	public TransactionDetails createTransaction(Double amount);
	abstract TransactionDetails prepareTransactionDetails(Order order);
}
