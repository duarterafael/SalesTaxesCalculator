package com.br.liferay.sales.taxes.calculator.service.impl;

import org.springframework.stereotype.Service;

import com.br.liferay.sales.taxes.calculator.dto.SalesTaxesDTO;
import com.br.liferay.sales.taxes.calculator.model.ProductsDescriptions;
import com.br.liferay.sales.taxes.calculator.model.SalesTaxesProductDefinition;
import com.br.liferay.sales.taxes.calculator.repository.IProductRepository;
import com.br.liferay.sales.taxes.calculator.repository.IProductTypeRepository;
import com.br.liferay.sales.taxes.calculator.utils.Constants;

@Service
public class SalesTaxesService{
	
	private IProductRepository iProductRepository;
	private IProductTypeRepository iProductTypeRepository;
	
	public SalesTaxesService (IProductRepository iProductRepository, IProductTypeRepository iProductTypeRepository) {
		this.iProductRepository = iProductRepository;
		this.iProductTypeRepository = iProductTypeRepository;
	}

	public SalesTaxesDTO calculateSalesTaxes(ProductsDescriptions productsDescriptions)
	{
		for (String productDescription : productsDescriptions.getProductsDescriptions()) {
			SalesTaxesProductDefinition a = productDescriptionParser(productDescription);
			
		}
		return null;
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
			// TODO: handle exception
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
			// TODO: handle exception
		}
		
		return salesTaxesProductDefinition;
		
	}
	
	
}
