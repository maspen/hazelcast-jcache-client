package com.example.client.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.entity.SimpleProduct;
import com.example.common.repository.IProductRepository;

@Service
public class SimpleProductService {

	@Autowired
	IProductRepository productRepository;
	
	// CRUD operations wrapping the same in service
	
	// create
	public SimpleProduct create(SimpleProduct newSimpleProduct) {
		System.out.println("SimpleProductService#create()");
		
		return productRepository.create(newSimpleProduct);
	}
	
	// read
	public SimpleProduct read(Long id) {
		System.out.println("SimpleProductService#read()");
		
		return productRepository.read(id);
	}
	
	// update
	public SimpleProduct update(SimpleProduct updatingSimpleProduct) {
		System.out.println("SimpleProductService#update()");
		
		return productRepository.update(updatingSimpleProduct);
	}
	
	// delete
	public void delete(Long id) {
		System.out.println("SimpleProductService#delete()");
		
		productRepository.delete(id);
	}
	
	// get the map ... to see if when a Product get created, it exists in the map ... no showing up in the cache
	public Map<Long, SimpleProduct> getMap() {
		System.out.println("SimpleProductService#getMap()");
		
		return productRepository.getProductMap();
	}
}
