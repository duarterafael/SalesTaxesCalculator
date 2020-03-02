package com.br.liferay.sales.taxes.calculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.liferay.sales.taxes.calculator.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByNameIgnoreCase(String name);
}
