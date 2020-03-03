package com.br.liferay.sales.taxes.calculator.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.liferay.sales.taxes.calculator.model.Product;
import com.br.liferay.sales.taxes.calculator.model.SalesTaxesProductDefinition;
import com.br.liferay.sales.taxes.calculator.model.SalesTaxesProductsDefinition;
import com.br.liferay.sales.taxes.calculator.repository.IProductRepository;
import com.br.liferay.sales.taxes.calculator.utils.Constants;
import com.br.liferay.sales.taxes.calculator.utils.Utils;

@Service
public class SalesTaxesService{
	
	private IProductRepository iProductRepository;
	
	public SalesTaxesService (IProductRepository iProductRepository) {
		this.iProductRepository = iProductRepository;
	}

	public SalesTaxesProductsDefinition calculateSalesTaxes(List<String> productsDescriptions)
	{
		SalesTaxesProductsDefinition salesTaxesProductsDefinition = new SalesTaxesProductsDefinition();
		for (String productDescription : productsDescriptions) {
			SalesTaxesProductDefinition salesTaxesProductDefinition = productDescriptionParser(productDescription);
			calculateSalesTaxes(salesTaxesProductDefinition);
			salesTaxesProductsDefinition.getSalesTaxesProductsDefinition().add(salesTaxesProductDefinition);
		}
		return salesTaxesProductsDefinition;
	}
	
	private void calculateSalesTaxes(SalesTaxesProductDefinition salesTaxesProductDefinition) {
		Optional<Product> product = iProductRepository.findByNameIgnoreCase(salesTaxesProductDefinition.getProductName());
		if(!product.isPresent())
		{
			//TODO: throw a custom exception
		}else
		{
			double tax = salesTaxesProductDefinition.getPrice()*(product.get().getProductType().getTax()/100);
			
			if(salesTaxesProductDefinition.isImported())
			{
				tax += salesTaxesProductDefinition.getPrice()*(Constants.IMPORTED_PRODUCT_TAX/100);
			}
			
			tax = Utils.ceil(tax);
			salesTaxesProductDefinition.setPriceWithTax(Utils.round(salesTaxesProductDefinition.getPrice()+tax));
		}
	}
 	
	private SalesTaxesProductDefinition productDescriptionParser(String productDescription)
	{
		SalesTaxesProductDefinition salesTaxesProductDefinition = new SalesTaxesProductDefinition();
		int firstBlanckSpace = productDescription.indexOf(Constants.BLANCK_SPACE);
		try
		{
			String productQtyStr = productDescription.substring(0, firstBlanckSpace);
			int productQty = Integer.valueOf(productQtyStr);
			salesTaxesProductDefinition.setQty(productQty);
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
		
		boolean isImported = productDescription.contains(Constants.IMPORTED_PRODUCT_DESCRIPTION);
		salesTaxesProductDefinition.setImported(isImported);
		
		String productName = null;
		if(isImported)
		{
			productName = productDescription.substring( Constants.IMPORTED_PRODUCT_DESCRIPTION.length() + productDescription.indexOf(Constants.IMPORTED_PRODUCT_DESCRIPTION) + 1, 
														productDescription.indexOf(" at"));	
		}else
		{
			productName = productDescription.substring(firstBlanckSpace + 1, productDescription.indexOf(" at"));
		}
		
		salesTaxesProductDefinition.setProductName(productName);
		
		try
		{
			String productPriceStr = productDescription.substring(productDescription.lastIndexOf(Constants.BLANCK_SPACE)+1);
			double productPrice = Double.valueOf(productPriceStr);
			salesTaxesProductDefinition.setPrice(productPrice);
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
		
		return salesTaxesProductDefinition;
	}
	
	
}
