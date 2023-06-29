package org.example.service;

import org.springframework.stereotype.Component;

/**
 * Generates invoice codes as sequential numbers.
 */
@Component
public class InvoiceCodeGenerator extends SequenceGenerator {

	/**
	 * Gets the name of the sequence to use for generating invoice codes.
	 *
	 * @return The name of the sequence to use for generating invoice codes.
	 */
	String getName(String brand, String period) {
		String seq = "";
		switch (brand) {
		case "AB":
			seq += "AB";
			break;
		case "RQ":
			seq += "RQ";
			break;
		default:
			break;
		}

		return seq;
	}
	
}
