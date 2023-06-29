package org.example.service;

import org.example.domain.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillingService {

	@Autowired
	private InvoiceCodeGenerator invoiceCodeGenerator;

	@Retryable(backoff = @Backoff(delay = 2000, multiplier = 2, random = true), maxAttempts = 40)
	public Invoice generateInvoice(String brand, String period) {
		// TODO 
		// return invoiceRepository.save(new Invoice(String.format("%06d", invoiceCodeGenerator.next())));
		return new Invoice(invoiceCodeGenerator.next(brand, period));
	}
	
	// TODO @Recover
	
	public void returnInvoice(String brand, String period, String value) {
		invoiceCodeGenerator.storeLost(brand, period, value);
	}	
}
