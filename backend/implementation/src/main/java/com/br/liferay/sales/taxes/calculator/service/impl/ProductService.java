package com.br.liferay.sales.taxes.calculator.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.liferay.sales.taxes.calculator.exception.CustomException;
import com.br.liferay.sales.taxes.calculator.model.Product;
import com.br.liferay.sales.taxes.calculator.repository.IProductRepository;
import com.br.liferay.sales.taxes.calculator.service.ICRUDService;

@Service
public class ProductService implements ICRUDService<Product, Product>{
	
	private IProductRepository iProductRepository;
	
	public ProductService (IProductRepository iProductRepository) {
		this.iProductRepository = iProductRepository;
	}

	@Override
	public List<Product> findAll() {
		return iProductRepository.findAll();
	}

	@Override
	public Optional<Product> findById(long id) {
		return iProductRepository.findById(id);
	}

	@Override
	public Product insert(Product product) {
		return iProductRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		Optional<Product> currentProduct = iProductRepository.findById(product.getId());
		if(currentProduct.isPresent())
		{
			return iProductRepository.save(product);
		}else
		{
			//TODO: Change this to a erro msg propertie
			throw new CustomException("Product type not found");
		}
		
	}

	@Override
	public void deleteById(long id) {
		iProductRepository.deleteById(id);
	}

}
