package com.algaworks.crm.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.crm.model.Categories;
import com.algaworks.crm.repository.CategoriesRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/categories")
public class CategoriesResources {

	@Autowired
	private CategoriesRepository repository;

	@GetMapping
	public @ResponseBody List<Categories> listOfCategories() {
		return repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categories> createdCategories(@RequestBody Categories categories, HttpServletResponse response) {
		Categories categoriesSave = repository.save(categories);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
		.buildAndExpand(categoriesSave.getCode()).toUri();
	response.setHeader("Location", uri.toASCIIString());
	
	return ResponseEntity.created(uri).body(categoriesSave); 
	}
	
	@GetMapping("/{code}")
	public Optional<Categories> getCode(@PathVariable Long code) {
		return repository.findById(code); 
	}
	
}
