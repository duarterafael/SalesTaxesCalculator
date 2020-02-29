package com.br.liferay.sales.taxes.calculator.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.liferay.sales.taxes.calculator.dto.ProductTypeDTO;
import com.br.liferay.sales.taxes.calculator.dto.UpdateProductTypeDTO;
import com.br.liferay.sales.taxes.calculator.model.ProductType;
import com.br.liferay.sales.taxes.calculator.service.IProductTypeService;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/api/v1/product/types")
public class ProductTypeController {

	private IProductTypeService productTypeService;
	private ModelMapper modelMapper;
	

	ProductTypeController(IProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
		this.modelMapper = new ModelMapper();
	}

	@GetMapping
	@ApiOperation(value= "List all product types", notes = "List all product types", responseContainer = "List")
	public ResponseEntity<List<ProductType>> findAll() {
		return new ResponseEntity<>(productTypeService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<ProductType> findById(@PathVariable long id) {
		return productTypeService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ApiOperation(value= "Save a new product type", notes = "Save a new product type", response = ProductType.class, responseContainer = "Object")
	public ProductType create(@RequestBody ProductTypeDTO productTypeDTO) {
		ProductType newProductType = this.modelMapper.map(productTypeDTO, ProductType.class);
		return productTypeService.insert(newProductType);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity delete(@PathVariable long id) {
		productTypeService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping(path ={"/{id}"})
	@ApiOperation(value= "Update a product type", notes = "Update a product type", response = ProductType.class, responseContainer = "Object")
	public ProductType update(@PathVariable long id, @RequestBody UpdateProductTypeDTO updateProductTypeDTO) {
		ProductType productType = this.modelMapper.map(updateProductTypeDTO, ProductType.class);
		productType.setId(id);
		return productTypeService.update(productType);
	}
	
}
