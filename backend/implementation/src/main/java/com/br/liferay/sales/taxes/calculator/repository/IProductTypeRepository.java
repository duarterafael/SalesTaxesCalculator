package com.br.liferay.sales.taxes.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.liferay.sales.taxes.calculator.model.ProductType;


@Repository
public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {
	
}
