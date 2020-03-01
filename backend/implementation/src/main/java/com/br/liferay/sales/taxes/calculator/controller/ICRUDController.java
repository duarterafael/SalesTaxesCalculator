package com.br.liferay.sales.taxes.calculator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICRUDController<T, U> {
	
	@GetMapping
	public ResponseEntity<List<T>> findAll();

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<T> findById(@PathVariable long id);
	
	@PostMapping
	public ResponseEntity<T> create(@RequestBody @Valid U dto);
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<T> delete(@PathVariable long id) ;
	
	@PutMapping(path ={"/{id}"})
	public ResponseEntity<T> update(@PathVariable long id,  @RequestBody @Valid U dto) ;
	
}
