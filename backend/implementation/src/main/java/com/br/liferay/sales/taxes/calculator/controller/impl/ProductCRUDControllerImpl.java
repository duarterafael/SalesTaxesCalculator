package com.br.liferay.sales.taxes.calculator.controller.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.liferay.sales.taxes.calculator.controller.ICRUDController;
import com.br.liferay.sales.taxes.calculator.dto.ProductDTO;
import com.br.liferay.sales.taxes.calculator.model.Product;
import com.br.liferay.sales.taxes.calculator.service.ICRUDService;
import com.br.liferay.sales.taxes.calculator.utils.Constants;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping(Constants.PRODUCT_URL)
public class ProductCRUDControllerImpl implements ICRUDController<Product, ProductDTO>{

	private ICRUDService<Product, Product> productService;
	private ModelMapper modelMapper;
	

	ProductCRUDControllerImpl(ICRUDService<Product, Product> productService) {
		this.productService = productService;
		this.modelMapper = new ModelMapper();
	}

	@Override
	@ApiOperation(value= "List all product", notes = "List all product", responseContainer = "List")
	public ResponseEntity<List<Product>> findAll() {
		return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
	}


	@Override
	@ApiOperation(value= "List product types by id", notes = "List product types by id", responseContainer = "Object")
	public ResponseEntity<Product> findById(long id) {
		return productService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}


	@Override
	@ApiOperation(value= "Save a new product type", notes = "Save a new product type", response = Product.class, responseContainer = "Object")
	public ResponseEntity<Product> create(ProductDTO t) {
		Product newProduct = this.modelMapper.map(t, Product.class);
		newProduct.setId(null);
		return new ResponseEntity<>(productService.insert(newProduct), HttpStatus.CREATED);
	}


	@Override
	@ApiOperation(value= "Delete a product type", notes = "Delete a product type", response = Product.class, responseContainer = "")
	public ResponseEntity delete(long id) {
		productService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	@Override
	@ApiOperation(value= "Update a product type", notes = "Update a product type", response = Product.class, responseContainer = "Object")
	public ResponseEntity<Product> update(long id, ProductDTO t) {
		Product product = this.modelMapper.map(t, Product.class);
		product.setId(id);
		return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
	}
	
}
