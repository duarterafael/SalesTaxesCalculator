package com.br.liferay.sales.taxes.calculator.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.liferay.sales.taxes.calculator.exception.CustomException;
import com.br.liferay.sales.taxes.calculator.model.ProductType;
import com.br.liferay.sales.taxes.calculator.repository.IProductTypeRepository;
import com.br.liferay.sales.taxes.calculator.service.ICRUDService;

@Service
public class ProductTypeService implements ICRUDService<ProductType, ProductType>{
	
	private IProductTypeRepository iProductTypeRepository;
	
	public ProductTypeService (IProductTypeRepository iProductTypeRepository) {
		this.iProductTypeRepository = iProductTypeRepository;
	}

	@Override
	public List<ProductType> findAll() {
		return iProductTypeRepository.findAll();
	}

	@Override
	public Optional<ProductType> findById(long id) {
		return iProductTypeRepository.findById(id);
	}

	@Override
	public ProductType insert(ProductType productType) {
		return iProductTypeRepository.save(productType);
	}

	@Override
	public ProductType update(ProductType productType) {
		Optional<ProductType> currentProductType = iProductTypeRepository.findById(productType.getId());
		if(currentProductType.isPresent())
		{
			return iProductTypeRepository.save(productType);
		}else
		{
			//TODO: Change this to a erro msg propertie
			throw new CustomException("Product type not found");
		}
		
	}

	@Override
	public void deleteById(long id) {
		iProductTypeRepository.deleteById(id);
	}

	

}
