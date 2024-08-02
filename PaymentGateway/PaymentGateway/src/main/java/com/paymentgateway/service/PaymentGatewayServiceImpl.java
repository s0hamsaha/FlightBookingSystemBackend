package com.paymentgateway.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.paymentgateway.entity.TransactionDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService
{
	
	private static final String key="rzp_test_CvF0k7iTzRpaJ1";
	private static final String secret_key="WTJaJh0datkbuFlMsYiSaxr4";
	private static final String currency="INR";
	
	
	@Override
	public TransactionDetails createTransaction(Double amount) {
		try {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("amount",amount*100);
			jsonObject.put("currency",currency);
			RazorpayClient razorpayClient=new RazorpayClient(key, secret_key);
			Order order = razorpayClient.orders.create(jsonObject);
			TransactionDetails transactionDetails=prepareTransactionDetails(order);
			return transactionDetails;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


	@Override
	public TransactionDetails prepareTransactionDetails(Order order) 
	{
		String orderId=order.get("id");
		String currency=order.get("currency");
		Integer amount=order.get("amount");
		
		TransactionDetails transactionDetails=new TransactionDetails(orderId,currency,amount,key);
		return transactionDetails;
	}

}
