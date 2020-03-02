package com.br.liferay.sales.taxes.calculator.model;

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
}
