package com.br.liferay.sales.taxes.calculator.model;

import java.util.ArrayList;
import java.util.List;

import com.br.liferay.sales.taxes.calculator.utils.Constants;
import com.br.liferay.sales.taxes.calculator.utils.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SalesTaxesProductsDefinition {
	private List<SalesTaxesProductDefinition> salesTaxesProductsDefinition;

	public SalesTaxesProductsDefinition() {
		salesTaxesProductsDefinition = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		double salesTaxes = 0;
		double total = 0;
		for (SalesTaxesProductDefinition salesTaxesProductDefinition : salesTaxesProductsDefinition) {
			str.append(salesTaxesProductDefinition.toString());
			str.append(System.getProperty("line.separator"));
			salesTaxes += salesTaxesProductDefinition.getPriceWithTax() - salesTaxesProductDefinition.getPrice();
			total += salesTaxesProductDefinition.getQty()*salesTaxesProductDefinition.getPriceWithTax();
		}
		str.append(Constants.SALES_TAXES_LABEL);
		str.append(Utils.round(salesTaxes));
		str.append(System.getProperty("line.separator"));
		str.append(Constants.TOTAL_LABEL);
		str.append(Utils.round(total));
		
		return str.toString();
	}
	
}
