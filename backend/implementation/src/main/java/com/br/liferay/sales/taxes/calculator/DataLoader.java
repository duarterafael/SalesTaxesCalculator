package com.br.liferay.sales.taxes.calculator;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.br.liferay.sales.taxes.calculator.model.Product;
import com.br.liferay.sales.taxes.calculator.model.ProductType;
import com.br.liferay.sales.taxes.calculator.service.ICRUDService;

@Component
public class DataLoader implements ApplicationRunner {

	private ICRUDService<ProductType, ProductType> productTypeService;
	private ICRUDService<Product, Product> productService;

	public DataLoader(ICRUDService<ProductType, ProductType> productTypeService, ICRUDService<Product, Product> productService) {
		this.productTypeService = productTypeService;
		this.productService = productService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ProductType book = seedProductType("Books", 0d);
		ProductType food = seedProductType("Food", 0d);
		ProductType medical = seedProductType("Medical", 0d);
		ProductType audioVisual = seedProductType("Audio-visual", 10d);
		ProductType perfumery = seedProductType("Perfumery", 10d);
		
		seedProduct("book", book);
		seedProduct("music CD", audioVisual);
		seedProduct("chocolate bar", food);
		seedProduct("box of chocolates", food);
		seedProduct("bottle of perfume", perfumery);
		seedProduct("packet of headache pills", medical);
	}
	
	private Product seedProduct(String name, ProductType productType) {
		Product product = new Product();
		product.setName(name);
		product.setProductType(productType);
		return productService.insert(product);
	}
	

	private ProductType seedProductType(String name, double tax) {
		ProductType productType = new ProductType();
		productType.setName(name);
		productType.setTax(tax);
		return productTypeService.insert(productType);
	}
}
