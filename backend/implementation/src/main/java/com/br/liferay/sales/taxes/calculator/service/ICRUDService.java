package com.br.liferay.sales.taxes.calculator.service;

import java.util.List;
import java.util.Optional;


public interface ICRUDService<T, U> {
	
	public List<T> findAll();
	
	public Optional<T> findById(long id);

	public T insert(U product);
	
	public T update(U product);
	
	public void deleteById(long id);
	
}
