package com.br.liferay.sales.taxes.calculator.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.liferay.sales.taxes.calculator.model.Product;
import com.br.liferay.sales.taxes.calculator.model.ProductsDescriptions;
import com.br.liferay.sales.taxes.calculator.model.SalesTaxesProductDefinition;
import com.br.liferay.sales.taxes.calculator.model.SalesTaxesProductsDefinition;
import com.br.liferay.sales.taxes.calculator.repository.IProductRepository;
import com.br.liferay.sales.taxes.calculator.repository.IProductTypeRepository;
import com.br.liferay.sales.taxes.calculator.utils.Constants;
import com.br.liferay.sales.taxes.calculator.utils.Utils;

@Service
public class SalesTaxesService{
	
	private IProductRepository iProductRepository;
	
	public SalesTaxesService (IProductRepository iProductRepository) {
		this.iProductRepository = iProductRepository;
	}

	public SalesTaxesProductsDefinition calculateSalesTaxes(ProductsDescriptions productsDescriptions)
	{
		SalesTaxesProductsDefinition salesTaxesProductsDefinition = new SalesTaxesProductsDefinition();
		for (String productDescription : productsDescriptions.getProductsDescriptions()) {
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
			if(salesTaxesProductDefinition.isImported())
			{
				salesTaxesProductDefinition.setPriceWithTax(salesTaxesProductDefinition.getPriceWithTax() + (salesTaxesProductDefinition.getPrice()*(Constants.IMPORTED_PRODUCT_TAX/100))); 
			}
			
			salesTaxesProductDefinition.setPriceWithTax(salesTaxesProductDefinition.getPriceWithTax() + (salesTaxesProductDefinition.getPrice()*(product.get().getProductType().getTax()/100))); 
		}
		
		salesTaxesProductDefinition.setPriceWithTax(Utils.ceil(salesTaxesProductDefinition.getPriceWithTax()));
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
			salesTaxesProductDefinition.setPriceWithTax(productPrice);
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
		
		return salesTaxesProductDefinition;
	}
	
	
}
