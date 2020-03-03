package com.br.liferay.sales.taxes.calculator.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.liferay.sales.taxes.calculator.exception.CustomException;
import com.br.liferay.sales.taxes.calculator.model.Product;
import com.br.liferay.sales.taxes.calculator.model.SalesTaxesProductDefinition;
import com.br.liferay.sales.taxes.calculator.model.SalesTaxesProductsDefinition;
import com.br.liferay.sales.taxes.calculator.repository.IProductRepository;
import com.br.liferay.sales.taxes.calculator.utils.Constants;
import com.br.liferay.sales.taxes.calculator.utils.Utils;

@Service
public class SalesTaxesService {

	private IProductRepository iProductRepository;

	public SalesTaxesService(IProductRepository iProductRepository) {
		this.iProductRepository = iProductRepository;
	}

	public SalesTaxesProductsDefinition calculateSalesTaxes(List<String> productsDescriptions) {
		int line = 1;
		try {
			SalesTaxesProductsDefinition salesTaxesProductsDefinition = new SalesTaxesProductsDefinition();
			for (String productDescription : productsDescriptions) {
				SalesTaxesProductDefinition salesTaxesProductDefinition = productDescriptionParser(productDescription);
				calculateSalesTaxes(salesTaxesProductDefinition);
				salesTaxesProductsDefinition.getSalesTaxesProductsDefinition().add(salesTaxesProductDefinition);
				line++;
			}

			return salesTaxesProductsDefinition;
		} catch (CustomException e) {
			// TODO: Change this to a error msg propertie
			throw new CustomException("Line: "+line+", error message: "+e.getMessage());
		}
	}

	private void calculateSalesTaxes(SalesTaxesProductDefinition salesTaxesProductDefinition) {
		Optional<Product> product = iProductRepository
				.findByNameIgnoreCase(salesTaxesProductDefinition.getProductName());
		if (!product.isPresent()) {
			// TODO: Change this to a erro msg propertie
			throw new CustomException("Product type not found");
		} else {
			double tax = salesTaxesProductDefinition.getPrice() * (product.get().getProductType().getTax() / 100);

			if (salesTaxesProductDefinition.isImported()) {
				tax += salesTaxesProductDefinition.getPrice() * (Constants.IMPORTED_PRODUCT_TAX / 100);
			}

			tax = Utils.ceil(tax);
			salesTaxesProductDefinition.setPriceWithTax(Utils.round(salesTaxesProductDefinition.getPrice() + tax));
		}
	}

	private SalesTaxesProductDefinition productDescriptionParser(String productDescription) {
		SalesTaxesProductDefinition salesTaxesProductDefinition = new SalesTaxesProductDefinition();
		int firstBlanckSpace = productDescription.indexOf(Constants.BLANCK_SPACE);
		String productQtyStr = new String();
		try {
			productQtyStr = productDescription.substring(0, firstBlanckSpace);
			int productQty = Integer.valueOf(productQtyStr);
			if(productQty <= 0)
			{
				throw new CustomException("Product quantity '"+productQty+"' most to be a positive integer number");
			}
			salesTaxesProductDefinition.setQty(productQty);
		} catch (NumberFormatException e) {
			// TODO: Change this to a error msg propertie
			throw new CustomException("Product quantity '"+productQtyStr+"' is not an integer");
		}

		boolean isImported = productDescription.contains(Constants.IMPORTED_PRODUCT_DESCRIPTION);
		salesTaxesProductDefinition.setImported(isImported);

		String productName = null;
		if (isImported) {
			productName = productDescription.substring(
					Constants.IMPORTED_PRODUCT_DESCRIPTION.length()
							+ productDescription.indexOf(Constants.IMPORTED_PRODUCT_DESCRIPTION) + 1,
					productDescription.indexOf(" at"));
		} else {
			productName = productDescription.substring(firstBlanckSpace + 1, productDescription.indexOf(" at"));
		}

		salesTaxesProductDefinition.setProductName(productName);

		String productPriceStr = new String();
		try {
			productPriceStr = productDescription
					.substring(productDescription.lastIndexOf(Constants.BLANCK_SPACE) + 1);
			double productPrice = Double.valueOf(productPriceStr);
			if(productPrice <= 0)
			{
				throw new CustomException("Product price '"+productPrice+"' most to be a positive floating point number");
			}
			salesTaxesProductDefinition.setPrice(productPrice);
		} catch (NumberFormatException e) {
			// TODO: Change this to a error msg propertie
			throw new CustomException("Product price '"+productQtyStr+"' is not a floating point number");
		}

		return salesTaxesProductDefinition;
	}

}
