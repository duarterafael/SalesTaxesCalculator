package com.br.liferay.sales.taxes.calculator.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.liferay.sales.taxes.calculator.service.impl.SalesTaxesService;
import com.br.liferay.sales.taxes.calculator.utils.Constants;

@RestController()
@RequestMapping(Constants.SALES_TAXR_URL)
public class SalesTaxesController {
	
	private SalesTaxesService salesTaxesService;
	
	SalesTaxesController(SalesTaxesService salesTaxesService) {
		this.salesTaxesService = salesTaxesService;
	}
	
	@PostMapping
	public ResponseEntity<String> CalculateSalesTaxes(@RequestBody @Valid List<String> productsDescriptions){
		return new ResponseEntity<>(salesTaxesService.calculateSalesTaxes(productsDescriptions).toString(), HttpStatus.OK);
	}
}
