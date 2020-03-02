package com.br.liferay.sales.taxes.calculator.model;

import com.br.liferay.sales.taxes.calculator.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesTaxesProductDefinition {
	private int qty;
	private boolean isImported;
	private String productName;
	private double price;
	private double priceWithTax;
	
	@Override
	public String toString() {
		return qty+Constants.BLANCK_SPACE+productName+Constants.COLONS+Constants.BLANCK_SPACE+priceWithTax;
	}
	
}
