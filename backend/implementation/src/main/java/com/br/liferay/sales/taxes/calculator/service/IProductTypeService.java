package com.br.liferay.sales.taxes.calculator.service;

import java.util.List;
import java.util.Optional;

import com.br.liferay.sales.taxes.calculator.model.ProductType;


public interface IProductTypeService {
	
	public List<ProductType> findAll();
	
	public Optional<ProductType> findById(long id);

	public ProductType insert(ProductType productType);
	
	public ProductType update(ProductType productType);
	
	public void deleteById(long id);
	
}
