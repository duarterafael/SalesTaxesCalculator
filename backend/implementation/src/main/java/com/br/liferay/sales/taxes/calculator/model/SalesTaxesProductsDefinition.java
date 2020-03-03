package com.br.liferay.sales.taxes.calculator.model;

import java.math.BigDecimal;
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
			str.append(Constants.NEW_LINE);
			salesTaxes += salesTaxesProductDefinition.getQty()*(salesTaxesProductDefinition.getPriceWithTax() - salesTaxesProductDefinition.getPrice());
			total += salesTaxesProductDefinition.getQty()*salesTaxesProductDefinition.getPriceWithTax();
		}
		str.append(Constants.SALES_TAXES_LABEL);
		str.append(BigDecimal.valueOf(Utils.round(salesTaxes)).setScale(2));
		str.append(Constants.NEW_LINE);
		str.append(Constants.TOTAL_LABEL);
		str.append(BigDecimal.valueOf(Utils.round(total)).setScale(2));
		
		return str.toString();
	}
	
}
