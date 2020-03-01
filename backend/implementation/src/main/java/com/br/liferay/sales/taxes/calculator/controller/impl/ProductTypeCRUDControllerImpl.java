package com.br.liferay.sales.taxes.calculator.controller.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.liferay.sales.taxes.calculator.controller.ICRUDController;
import com.br.liferay.sales.taxes.calculator.dto.ProductTypeDTO;
import com.br.liferay.sales.taxes.calculator.model.ProductType;
import com.br.liferay.sales.taxes.calculator.service.ICRUDService;
import com.br.liferay.sales.taxes.calculator.utils.Constants;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping(Constants.PRODUCT_TYPE_URL)
public class ProductTypeCRUDControllerImpl implements ICRUDController<ProductType, ProductTypeDTO>{

	private ICRUDService<ProductType, ProductType> productTypeService;
	private ModelMapper modelMapper;
	

	ProductTypeCRUDControllerImpl(ICRUDService<ProductType, ProductType> productTypeService) {
		this.productTypeService = productTypeService;
		this.modelMapper = new ModelMapper();
	}

	@Override
	@ApiOperation(value= "List all product types", notes = "List all product types", responseContainer = "List")
	public ResponseEntity<List<ProductType>> findAll() {
		return new ResponseEntity<>(productTypeService.findAll(), HttpStatus.OK);
	}


	@Override
	@ApiOperation(value= "List product types by id", notes = "List product types by id", responseContainer = "Object")
	public ResponseEntity<ProductType> findById(long id) {
		return productTypeService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}


	@Override
	@ApiOperation(value= "Save a new product type", notes = "Save a new product type", response = ProductType.class, responseContainer = "Object")
	public ResponseEntity<ProductType> create(ProductTypeDTO t) {
		ProductType newProductType = this.modelMapper.map(t, ProductType.class);
		return new ResponseEntity<>(productTypeService.insert(newProductType), HttpStatus.CREATED);
	}


	@Override
	@ApiOperation(value= "Delete a product type", notes = "Delete a product type", response = ProductType.class, responseContainer = "")
	public ResponseEntity delete(long id) {
		productTypeService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	@Override
	@ApiOperation(value= "Update a product type", notes = "Update a product type", response = ProductType.class, responseContainer = "Object")
	public ResponseEntity<ProductType> update(long id, ProductTypeDTO t) {
		ProductType productType = this.modelMapper.map(t, ProductType.class);
		productType.setId(id);
		return new ResponseEntity<>(productTypeService.update(productType), HttpStatus.OK);
	}

	
	
}
